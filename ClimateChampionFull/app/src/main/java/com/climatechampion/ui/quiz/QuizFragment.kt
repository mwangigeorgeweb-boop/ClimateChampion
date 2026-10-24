package com.climatechampion.ui.quiz

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import androidx.core.view.isVisible
import com.climatechampion.R
import com.climatechampion.databinding.FragmentQuizBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.utils.toast
import com.climatechampion.viewmodel.QuizViewModel
import com.google.android.material.button.MaterialButton

/**
 * Quiz screen — one question at a time with a timer.
 *
 * Default (assessment) mode: tapping an option locks it in and highlights it neutrally.
 * No "correct / wrong" is shown and the answer is never revealed during the quiz.
 * The Next / Finish button appears after ANY answer (or a timeout).
 * Results are shown only on the result screen.
 *
 * Set INSTANT_FEEDBACK = true for practice mode (shows correct/incorrect + explanation).
 */
@Suppress("KotlinConstantConditions", "SimplifyBooleanWithConstants")
class QuizFragment : Fragment() {

    companion object {
        private const val INSTANT_FEEDBACK = false
    }

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private val args: QuizFragmentArgs by navArgs()
    private val viewModel: QuizViewModel by viewModels()

    private var currentIsTrueFalse = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupObservers()
        viewModel.loadQuestions(args.topicId) // no-op if a quiz is already in progress
    }

    private fun setupClickListeners() {
        binding.btnNext.setOnClickListener {
            viewModel.nextQuestion()
        }
    }

    private fun setupObservers() {
        viewModel.questions.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.quizContent.hide()
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val questionList = result.data
                    if (questionList.isEmpty()) {
                        binding.tvEmpty.show()
                    } else {
                        binding.quizContent.show()
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.tvEmpty.text = result.message
                    binding.tvEmpty.show()
                }
            }
        }

        // NOTE: keep this observer registered BEFORE selectedAnswer, so that when the view
        // is re-created the question is bound first and the locked state is re-applied after.
        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            question ?: return@observe
            resetAnswerButtons()
            binding.tvFeedback.hide()
            binding.tvExplanation.hide()
            binding.btnNext.hide()

            binding.tvQuestion.text = question.questionText

            val qIndex = viewModel.currentQuestionIndex.value ?: 0
            val total = viewModel.getTotalQuestions()
            binding.tvProgress.text = getString(R.string.question_progress, qIndex + 1, total)
            binding.progressBarQuestion.max = total
            binding.progressBarQuestion.progress = qIndex + 1

            // "Finish" on the last question, "Next" otherwise
            binding.btnNext.text = getString(
                if (qIndex + 1 >= total) R.string.quiz_btn_finish else R.string.quiz_btn_next
            )

            currentIsTrueFalse = question.type.equals("TRUE_FALSE", ignoreCase = true)

            if (currentIsTrueFalse) {
                binding.optionsGroup.hide()
                binding.trueFalseGroup.show()
                binding.btnTrue.setOnClickListener { viewModel.selectAnswer("True") }
                binding.btnFalse.setOnClickListener { viewModel.selectAnswer("False") }
            } else {
                binding.trueFalseGroup.hide()
                binding.optionsGroup.show()
                val options = question.options
                optionButtons().forEachIndexed { i, btn ->
                    if (i < options.size) {
                        btn.show()
                        btn.text = options[i]
                        btn.setOnClickListener { viewModel.selectAnswer(options[i]) }
                    } else {
                        btn.hide()
                    }
                }
            }
        }

        viewModel.timerSeconds.observe(viewLifecycleOwner) { secs ->
            binding.tvTimer.text = getString(R.string.timer_format, secs)
            binding.tvTimer.setTextColor(
                if (secs <= 10) Color.RED
                else ContextCompat.getColor(requireContext(), android.R.color.black)
            )
            binding.progressBarTimer.progress = secs.toInt()
            binding.progressBarTimer.max = 30
        }

        // Answer locked (or timed out): highlight neutrally and ALWAYS allow moving on.
        viewModel.selectedAnswer.observe(viewLifecycleOwner) { answer ->
            answer ?: return@observe
            lockAnswerButtons(answer)
            binding.btnNext.show()

            if (answer == QuizViewModel.TIMEOUT && !INSTANT_FEEDBACK) {
                binding.tvFeedback.text = getString(R.string.quiz_time_up)
                binding.tvFeedback.setTextColor(
                    ContextCompat.getColor(requireContext(), android.R.color.black)
                )
                binding.tvFeedback.show()
            }
        }

        // Practice mode only. In assessment mode nothing is revealed here.
        viewModel.answerFeedback.observe(viewLifecycleOwner) { feedback ->
            if (!INSTANT_FEEDBACK) return@observe
            feedback ?: return@observe
            binding.tvFeedback.show()
            binding.tvExplanation.show()

            if (feedback.isCorrect) {
                binding.tvFeedback.text = getString(R.string.correct)
                binding.tvFeedback.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.correct_green)
                )
            } else {
                binding.tvFeedback.text = getString(
                    R.string.incorrect_answer, feedback.correctAnswer
                )
                binding.tvFeedback.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.incorrect_red)
                )
            }
            binding.tvExplanation.text = feedback.explanation
        }

        viewModel.quizResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val quizData = result.data
                    val action = QuizFragmentDirections
                        .actionQuizFragmentToQuizResultFragment(
                            quizData.score,
                            quizData.totalQuestions,
                            quizData.percentage,
                            quizData.passed,
                            quizData.pointsEarned,
                            args.topicId
                        )
                    // Remove the quiz from the back stack so Back / "Topics" on the result
                    // screen can't return here and replay this result.
                    findNavController().navigate(
                        action,
                        navOptions { popUpTo(R.id.quizFragment) { inclusive = true } }
                    )
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    toast(result.message) // Finish stays visible so the user can retry
                }
            }
        }
    }

    private fun optionButtons() = listOf(
        binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4
    )

    private fun allAnswerButtons() = optionButtons() + listOf(binding.btnTrue, binding.btnFalse)

    private fun resetAnswerButtons() {
        val textColor = ContextCompat.getColor(requireContext(), R.color.text_primary)
        val strokeColor = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(), R.color.primary_green)
        )
        val strokePx = (2 * resources.displayMetrics.density).toInt()
        allAnswerButtons().forEach {
            it.isEnabled = true
            it.isClickable = true
            it.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.option_default)
            )
            // Dark text + green outline so True/False and options are clearly visible
            it.setTextColor(textColor)
            (it as? MaterialButton)?.apply {
                strokeWidth = strokePx
                setStrokeColor(strokeColor)
            }
        }
    }

    /** Locks every button and marks the chosen one with a neutral color (no right/wrong hint). */
    private fun lockAnswerButtons(answer: String) {
        allAnswerButtons().forEach { it.isClickable = false }
        if (answer == QuizViewModel.TIMEOUT) return

        val selected = if (currentIsTrueFalse) {
            if (answer.equals("True", ignoreCase = true)) binding.btnTrue else binding.btnFalse
        } else {
            optionButtons().firstOrNull {
                it.isVisible && it.text.toString() == answer
            }
        }
        selected?.setBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.quiz_option_selected)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
