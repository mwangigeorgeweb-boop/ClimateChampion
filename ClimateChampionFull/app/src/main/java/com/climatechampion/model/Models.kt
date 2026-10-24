package com.climatechampion.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Locale

// ─── User ────────────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class User(
    val userId: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val totalPoints: Int = 0,
    val level: String = "Expert",
    val createdAt: Timestamp = Timestamp.now(),
    val lastActive: Timestamp = Timestamp.now()
)

// ─── UserProgress ─────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class UserProgress(
    val topicId: String = "",
    val completed: Boolean = false,
    val percentComplete: Int = 0,
    val lastUpdated: Timestamp = Timestamp.now()
)

// ─── QuizResult ───────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class QuizResult(
    val resultId: String = "",
    val topicId: String = "",
    val score: Int = 0,
    val totalQuestions: Int = 0,
    val percentage: Float = 0f,
    val passed: Boolean = false,
    val takenAt: Timestamp = Timestamp.now()
)

// ─── Subtopic ─────────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class Subtopic(
    val subtopicId: String = "",
    val title: String = "",
    val summary: String = "",
    val content: String = "",
    val orderIndex: Int = 0,
    val readTimeMinutes: Int = 0
)

// ─── Topic ────────────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class Topic(
    val topicId: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val thumbnailUrl: String = "",
    val difficulty: String = "Expert",
    val pointsReward: Int = 100,
    val orderIndex: Int = 0,

    @field:JvmField
    val isPublished: Boolean = true,

    val createdAt: Timestamp = Timestamp.now(),
    val subtopics: List<Subtopic> = emptyList() // Added nested subtopics from database
)

// ─── Video ────────────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class Video(
    val videoId: String = "",
    val title: String = "",
    val description: String = "",
    val topicId: String = "",
    val thumbnailUrl: String = "",
    val videoUrl: String = "",
    val durationSeconds: Int = 0,
    val pointsReward: Int = 50,
    val orderIndex: Int = 0,

    @field:JvmField
    val isPublished: Boolean = true,

    val createdAt: Timestamp = Timestamp.now()
) {
    /** Returns duration formatted as MM:SS with explicit Locale */
    fun formattedDuration(): String {
        val minutes = durationSeconds / 60
        val seconds = durationSeconds % 60
        return String.format(Locale.US, "%02d:%02d", minutes, seconds)
    }

    /** Videos must be at least 5 minutes (300 seconds) */
    fun isMinimumLength(): Boolean = durationSeconds >= 300
}

// ─── Question ─────────────────────────────────────────────────────────────────

@Suppress("unused")
enum class QuestionType { MCQ, TRUE_FALSE, SHORT_ANSWER }

@IgnoreExtraProperties
data class Question(
    val questionId: String = "",
    val topicId: String = "",
    val questionText: String = "",
    val type: String = QuestionType.MCQ.name,
    val options: List<String> = emptyList(),
    val correctAnswer: String = "",
    val explanation: String = "",
    val difficulty: String = "Expert",
    val points: Int = 20,
    val orderIndex: Int = 0
)

// ─── Quiz Submission / Response ───────────────────────────────────────────────

@IgnoreExtraProperties
data class QuizSubmission(
    val userId: String = "",
    val topicId: String = "",
    val answers: List<UserAnswer> = emptyList()
)

@IgnoreExtraProperties
data class UserAnswer(
    val questionId: String = "",
    val selectedAnswer: String = ""
)

@IgnoreExtraProperties
data class QuizResponse(
    val score: Int = 0,
    val totalQuestions: Int = 0,
    val percentage: Float = 0f,
    val passed: Boolean = false,
    val pointsEarned: Int = 0,
    val results: List<AnswerResult> = emptyList()
)

@IgnoreExtraProperties
data class AnswerResult(
    val questionId: String = "",
    val isCorrect: Boolean = false,
    val correctAnswer: String = "",
    val explanation: String = ""
)

// ─── LeaderboardEntry ─────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class LeaderboardEntry(
    val userId: String = "",
    val displayName: String = "",
    val photoUrl: String = "",
    val totalPoints: Int = 0,
    val quizzesCompleted: Int = 0,
    val videosWatched: Int = 0,
    val topicsCompleted: Int = 0,
    val level: String = "Expert",
    val weeklyPoints: Int = 0,
    val lastUpdated: Timestamp = Timestamp.now(),
    var rank: Int = 0
) {
    /** Derive level badge from points */
    fun getLevelBadge(): String = when {
        totalPoints >= 5000 -> "Expert"
        totalPoints >= 2000 -> "Intermediate"
        else -> "Beginner"
    }
}

// ─── Quiz ─────────────────────────────────────────────────────────────────────

@IgnoreExtraProperties
data class Quiz(
    val quizId: String = "",
    val topicId: String = "",
    val title: String = "",
    val description: String = "",
    val points: Int = 30,

    @field:JvmField
    val isActive: Boolean = true,

    val questions: List<QuizQuestion> = emptyList(),
    val createdAt: Timestamp = Timestamp.now()
)

@IgnoreExtraProperties
data class QuizQuestion(
    val questionId: String = "",
    val questionText: String = "",
    val options: List<String> = emptyList(),
    val correctAnswerIndex: Int = 0,
    val explanation: String = ""
)