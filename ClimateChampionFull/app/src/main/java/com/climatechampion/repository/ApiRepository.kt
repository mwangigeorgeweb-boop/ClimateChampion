package com.climatechampion.repository

import com.climatechampion.model.*
import com.climatechampion.network.ApiClient
import com.climatechampion.network.NetworkResult
import com.google.gson.Gson
import com.google.gson.JsonObject
import javax.inject.Singleton

/**
 * Repository for all REST API calls via Retrofit.
 * Falls back to Firestore via [FirestoreRepository] when needed.
 */
@Singleton
class ApiRepository(
    private val firestoreRepository: FirestoreRepository = FirestoreRepository()
) {

    private val api = ApiClient.apiService

    // ─── Topics ───────────────────────────────────────────────────────────────

    suspend fun getAllTopics(): NetworkResult<List<Topic>> {
        return try {
            val response = api.getAllTopics()
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                // Fallback to Firestore on API failure
                firestoreRepository.getAllTopics()
            }
        } catch (e: Exception) {
            firestoreRepository.getAllTopics()
        }
    }

    suspend fun getTopicById(topicId: String): NetworkResult<Topic> {
        return try {
            val response = api.getTopicById(topicId)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                firestoreRepository.getTopicById(topicId)
            }
        } catch (e: Exception) {
            firestoreRepository.getTopicById(topicId)
        }
    }

    suspend fun getSubtopics(topicId: String): NetworkResult<List<Subtopic>> {
        return try {
            val response = api.getSubtopics(topicId)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                firestoreRepository.getSubtopics(topicId)
            }
        } catch (e: Exception) {
            firestoreRepository.getSubtopics(topicId)
        }
    }

    suspend fun getQuestions(topicId: String): NetworkResult<List<Question>> {
        return try {
            val response = api.getQuestions(topicId)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                firestoreRepository.getQuestions(topicId)
            }
        } catch (e: Exception) {
            firestoreRepository.getQuestions(topicId)
        }
    }

    // ─── Videos ───────────────────────────────────────────────────────────────

    suspend fun getAllVideos(): NetworkResult<List<Video>> {
        return try {
            val response = api.getAllVideos()
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                firestoreRepository.getAllVideos()
            }
        } catch (e: Exception) {
            firestoreRepository.getAllVideos()
        }
    }

    suspend fun getVideosByTopic(topicId: String): NetworkResult<List<Video>> {
        return try {
            val response = api.getVideosByTopic(topicId)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                firestoreRepository.getVideosByTopic(topicId)
            }
        } catch (e: Exception) {
            firestoreRepository.getVideosByTopic(topicId)
        }
    }

    // ─── Leaderboard ─────────────────────────────────────────────────────────

    suspend fun getLeaderboard(): NetworkResult<List<LeaderboardEntry>> {
        return try {
            val response = api.getLeaderboard()
            if (response.isSuccessful && response.body() != null) {
                val entries = response.body()!!.mapIndexed { i, e -> e.copy(rank = i + 1) }
                NetworkResult.Success(entries)
            } else {
                firestoreRepository.getLeaderboard()
            }
        } catch (e: Exception) {
            firestoreRepository.getLeaderboard()
        }
    }

    suspend fun getWeeklyLeaderboard(): NetworkResult<List<LeaderboardEntry>> {
        return try {
            val response = api.getWeeklyLeaderboard()
            if (response.isSuccessful && response.body() != null) {
                val entries = response.body()!!.mapIndexed { i, e -> e.copy(rank = i + 1) }
                NetworkResult.Success(entries)
            } else {
                firestoreRepository.getWeeklyLeaderboard()
            }
        } catch (e: Exception) {
            firestoreRepository.getWeeklyLeaderboard()
        }
    }

    // ─── Quiz Submission ──────────────────────────────────────────────────────

    /**
     * Submits quiz answers to the REST API and returns the graded response.
     * If the API is unreachable (e.g. Cloud Functions not deployed / BASE_URL is localhost)
     * or returns an error, the quiz is graded on the device using the questions in Firestore.
     */
    suspend fun submitQuiz(submission: QuizSubmission): NetworkResult<QuizResponse> {
        return try {
            val response = api.submitQuiz(submission)
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                gradeLocally(submission, "Failed to submit quiz: ${response.message()}")
            }
        } catch (e: Exception) {
            gradeLocally(submission, "Network error: ${e.message}")
        }
    }

    /**
     * Local grading fallback. Mirrors the server rules from the README:
     * pass = 70% or more (+100 points), perfect score = +200 points.
     * An empty answer (timeout) counts as wrong.
     */
    private suspend fun gradeLocally(
        submission: QuizSubmission,
        reason: String
    ): NetworkResult<QuizResponse> {
        val questionsResult = firestoreRepository.getQuestions(submission.topicId)
        if (questionsResult !is NetworkResult.Success) {
            return NetworkResult.Error("$reason (could not load questions to grade locally)")
        }
        val questions = questionsResult.data
        if (questions.isEmpty()) return NetworkResult.Error("$reason (no questions found to grade)")

        val byId = questions.associateBy { it.questionId }
        var score = 0
        for (answer in submission.answers) {
            val question = byId[answer.questionId] ?: continue
            if (answer.selectedAnswer.isNotBlank() &&
                answer.selectedAnswer.equals(question.correctAnswer, ignoreCase = true)
            ) {
                score++
            }
        }

        val total = questions.size
        val percentage = score * 100f / total
        val passed = percentage >= 70f
        val points = when {
            percentage >= 100f -> 200
            passed -> 100
            else -> 0
        }

        // Build the response from its field names so this doesn't depend on its constructor.
        val json = JsonObject().apply {
            addProperty("score", score)
            addProperty("totalQuestions", total)
            addProperty("percentage", percentage)
            addProperty("passed", passed)
            addProperty("pointsEarned", points)
        }
        val graded = Gson().fromJson(json, QuizResponse::class.java)

        // Safety check: if the field names didn't map, fail loudly instead of showing wrong data.
        if (graded == null ||
            graded.score.toString() != score.toString() ||
            graded.totalQuestions.toString() != total.toString()
        ) {
            return NetworkResult.Error("Could not read the quiz result. Please try again.")
        }
        return NetworkResult.Success(graded)
    }

    // ─── Progress Updates ─────────────────────────────────────────────────────

    suspend fun updateProgress(
        userId: String,
        progress: UserProgress
    ): NetworkResult<Unit> {
        // Write to Firestore directly; API call optional
        return firestoreRepository.updateProgress(userId, progress)
    }

    suspend fun markVideoWatched(userId: String, videoId: String): NetworkResult<Unit> {
        return try {
            api.markVideoWatched(mapOf("userId" to userId, "videoId" to videoId))
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to mark video as watched: ${e.message}")
        }
    }
}
