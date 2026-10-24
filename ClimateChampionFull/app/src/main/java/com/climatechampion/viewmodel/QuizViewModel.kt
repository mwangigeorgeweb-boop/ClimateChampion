package com.climatechampion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatechampion.model.*
import com.climatechampion.network.NetworkResult
import com.climatechampion.repository.ApiRepository
import com.climatechampion.repository.FirestoreRepository
import com.climatechampion.utils.Constants
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel for the quiz screen and quiz result screen.
 * Manages question state, timer, and submission.
 *
 * Flow: select answer -> answer is locked -> user presses Next -> ... -> last Next submits.
 * Pressing Next is allowed after ANY answer (or a timeout), never only after a correct one.
 */
class QuizViewModel : ViewModel() {

    companion object {
        /** Value stored in [selectedAnswer] when the timer runs out. */
        const val TIMEOUT = "TIMEOUT"
        private const val TAG = "QuizViewModel"
    }

    private val apiRepo = ApiRepository()
    private val firestoreRepo = FirestoreRepository()
    private val auth = FirebaseAuth.getInstance()

    // ─── Questions ────────────────────────────────────────────────────────────

    private val _questions = MutableLiveData<NetworkResult<List<Question>>>()
    val questions: LiveData<NetworkResult<List<Question>>> = _questions

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex

    private val _currentQuestion = MutableLiveData<Question?>()
    val currentQuestion: LiveData<Question?> = _currentQuestion

    // ─── Timer ────────────────────────────────────────────────────────────────

    private val _timerSeconds = MutableLiveData(Constants.QUIZ_TIMER_SECONDS)
    val timerSeconds: LiveData<Long> = _timerSeconds

    private var timerJob: Job? = null

    // ─── Answers ─────────────────────────────────────────────────────────────

    private val userAnswers = mutableListOf<UserAnswer>()
    private var questionList: List<Question> = emptyList()
    private var currentTopicId: String = ""

    private var loadJob: Job? = null
    private var isSubmitting = false

    private val _selectedAnswer = MutableLiveData<String?>()
    val selectedAnswer: LiveData<String?> = _selectedAnswer

    /**
     * Kept for the optional "instant feedback" (practice) mode.
     * The default no-spoiler UI ignores this.
     */
    private val _answerFeedback = MutableLiveData<AnswerFeedback?>()
    val answerFeedback: LiveData<AnswerFeedback?> = _answerFeedback

    // ─── Quiz Result ──────────────────────────────────────────────────────────

    private val _quizResult = MutableLiveData<NetworkResult<QuizResponse>>()
    val quizResult: LiveData<NetworkResult<QuizResponse>> = _quizResult

    // ─── Load Questions ───────────────────────────────────────────────────────

    fun loadQuestions(topicId: String) {
        // The Fragment calls this from onViewCreated, which also runs after rotation or
        // when returning from the back stack. Don't wipe an in-progress quiz.
        if (currentTopicId == topicId && (questionList.isNotEmpty() || loadJob?.isActive == true)) return

        currentTopicId = topicId
        userAnswers.clear()
        isSubmitting = false
        _currentQuestionIndex.value = 0
        _selectedAnswer.value = null
        _answerFeedback.value = null

        loadJob = viewModelScope.launch {
            _questions.value = NetworkResult.Loading()
            val result = apiRepo.getQuestions(topicId)
            _questions.value = result
            if (result is NetworkResult.Success<List<Question>>) {
                questionList = result.data
                _currentQuestion.value = questionList.firstOrNull()
                if (questionList.isNotEmpty()) startTimer()
            }
        }
    }

    // ─── Timer Logic ──────────────────────────────────────────────────────────

    private fun startTimer() {
        timerJob?.cancel()
        _timerSeconds.value = Constants.QUIZ_TIMER_SECONDS
        timerJob = viewModelScope.launch {
            while ((_timerSeconds.value ?: 0) > 0) {
                delay(1000L)
                _timerSeconds.value = (_timerSeconds.value ?: 1) - 1
            }
            // Time's up — record a blank answer and let the user move on
            onTimeUp()
        }
    }

    private fun onTimeUp() {
        val currentQ = _currentQuestion.value ?: return
        if (_selectedAnswer.value == null) {
            _selectedAnswer.value = TIMEOUT
            _answerFeedback.value = AnswerFeedback(
                isCorrect = false,
                correctAnswer = currentQ.correctAnswer,
                explanation = "Time's up! ${currentQ.explanation}"
            )
            recordAnswer(currentQ, "")
        }
    }

    // ─── Answer Handling ──────────────────────────────────────────────────────

    /** Called when the user selects an answer option. The first tap locks the answer. */
    fun selectAnswer(answer: String) {
        val currentQ = _currentQuestion.value ?: return
        if (_selectedAnswer.value != null) return // already answered / locked

        timerJob?.cancel()
        _selectedAnswer.value = answer

        val isCorrect = answer.equals(currentQ.correctAnswer, ignoreCase = true)
        _answerFeedback.value = AnswerFeedback(
            isCorrect = isCorrect,
            correctAnswer = currentQ.correctAnswer,
            explanation = currentQ.explanation
        )
        recordAnswer(currentQ, answer)
    }

    private fun recordAnswer(question: Question, answer: String) {
        userAnswers.add(UserAnswer(questionId = question.questionId, selectedAnswer = answer))
    }

    /**
     * Moves to the next question, or submits the quiz after the last one.
     * Ignored until the current question has an answer (prevents double-tap skipping).
     */
    fun nextQuestion() {
        if (_selectedAnswer.value == null) return

        val nextIndex = (_currentQuestionIndex.value ?: 0) + 1
        if (nextIndex < questionList.size) {
            _selectedAnswer.value = null
            _answerFeedback.value = null
            _currentQuestionIndex.value = nextIndex
            _currentQuestion.value = questionList[nextIndex]
            startTimer()
        } else {
            // Last question: keep the answer locked and Next visible so a failed
            // submission can simply be retried by pressing Finish again.
            submitQuiz()
        }
    }

    // ─── Submit Quiz ──────────────────────────────────────────────────────────

    private fun submitQuiz() {
        if (isSubmitting) return

        val user = auth.currentUser
        if (user == null) {
            // Previously this returned silently and the quiz appeared frozen.
            _quizResult.value = NetworkResult.Error("Your session has expired. Please sign in again.")
            return
        }
        val userId = user.uid
        isSubmitting = true

        viewModelScope.launch {
            _quizResult.value = NetworkResult.Loading()
            val submission = QuizSubmission(
                userId = userId,
                topicId = currentTopicId,
                answers = userAnswers.toList()
            )
            val result = apiRepo.submitQuiz(submission)

            if (result is NetworkResult.Success<QuizResponse>) {
                val quizResponse = result.data
                // A Firestore failure must never stop the user from seeing their result.
                try {
                    firestoreRepo.saveQuizResult(
                        userId = userId,
                        result = QuizResult(
                            topicId = currentTopicId,
                            score = quizResponse.score,
                            totalQuestions = quizResponse.totalQuestions,
                            percentage = quizResponse.percentage,
                            passed = quizResponse.passed,
                            takenAt = Timestamp.now()
                        )
                    )

                    if (quizResponse.pointsEarned > 0) {
                        val added = firestoreRepo.addPoints(userId, quizResponse.pointsEarned)
                        if (added is NetworkResult.Error) {
                            Log.e(TAG, "Saving points failed: ${added.message}")
                        }
                        firestoreRepo.updateLeaderboard(
                            userId = userId,
                            displayName = user.displayName ?: "Champion",
                            photoUrl = user.photoUrl?.toString() ?: "",
                            pointsEarned = quizResponse.pointsEarned,
                            quizPassed = quizResponse.passed
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Saving quiz result/points failed", e)
                }
            } else {
                isSubmitting = false // allow retry
            }
            _quizResult.value = result
        }
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    fun getTotalQuestions(): Int = questionList.size

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

/** Holds feedback data (only displayed when the Fragment's INSTANT_FEEDBACK flag is on). */
data class AnswerFeedback(
    val isCorrect: Boolean,
    val correctAnswer: String,
    val explanation: String
)
