package com.climatechampion.repository

import com.climatechampion.model.*
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.Constants
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

/**
 * Repository for all direct Firestore database operations.
 * Wraps results in [NetworkResult] for consistent UI state handling.
 */
@Singleton
class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    // ─── User Operations ──────────────────────────────────────────────────────

    /**
     * Creates or updates a user document in Firestore.
     */
    suspend fun createOrUpdateUser(user: User): NetworkResult<Unit> {
        return try {
            db.collection(Constants.COLLECTION_USERS)
                .document(user.userId)
                .set(user)
                .await()
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to save user: ${e.message}")
        }
    }

    /**
     * Fetches a single user document by userId.
     */
    suspend fun getUser(userId: String): NetworkResult<User> {
        return try {
            val doc = db.collection(Constants.COLLECTION_USERS)
                .document(userId)
                .get()
                .await()
            val user = doc.toObject(User::class.java)
                ?: return NetworkResult.Error("User not found")
            NetworkResult.Success(user.copy(userId = doc.id))
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch user: ${e.message}")
        }
    }

    /**
     * Updates a user's total points and recalculates their level.
     * If the user's profile document doesn't exist yet it is created, because
     * transaction.update() on a missing document fails and the points were silently lost.
     */
    suspend fun addPoints(userId: String, pointsToAdd: Int): NetworkResult<Unit> {
        return try {
            val userRef = db.collection(Constants.COLLECTION_USERS).document(userId)
            val authUser = FirebaseAuth.getInstance().currentUser
            db.runTransaction { transaction ->
                val snapshot = transaction.get(userRef)
                val currentPoints = snapshot.getLong("totalPoints")?.toInt() ?: 0
                val newPoints = currentPoints + pointsToAdd
                val newLevel = when {
                    newPoints >= Constants.LEVEL_EXPERT_THRESHOLD -> Constants.LEVEL_EXPERT
                    newPoints >= Constants.LEVEL_INTERMEDIATE_THRESHOLD -> Constants.LEVEL_INTERMEDIATE
                    else -> Constants.LEVEL_BEGINNER
                }
                val updates = mapOf(
                    "totalPoints" to newPoints,
                    "level" to newLevel,
                    "lastActive" to Timestamp.now()
                )
                if (snapshot.exists()) {
                    transaction.update(userRef, updates)
                } else {
                    // Profile was never created: create it now with the basics.
                    transaction.set(
                        userRef,
                        updates + mapOf(
                            "userId" to userId,
                            "email" to (authUser?.email ?: ""),
                            "displayName" to (authUser?.displayName ?: ""),
                            "photoUrl" to (authUser?.photoUrl?.toString() ?: "")
                        )
                    )
                }
            }.await()
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to update points: ${e.message}")
        }
    }

    /**
     * Updates the lastActive timestamp for daily login tracking.
     */
    suspend fun updateLastActive(userId: String): NetworkResult<Unit> {
        return try {
            db.collection(Constants.COLLECTION_USERS)
                .document(userId)
                .update("lastActive", Timestamp.now())
                .await()
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to update last active: ${e.message}")
        }
    }

    // ─── Topics ───────────────────────────────────────────────────────────────

    /**
     * Fetches all published topics and sorts them in Kotlin to bypass missing composite indexes.
     */
    suspend fun getAllTopics(): NetworkResult<List<Topic>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_TOPICS)
                .whereEqualTo("isPublished", true)
                .get()
                .await()
            val topics = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Topic::class.java)?.copy(topicId = doc.id)
            }.sortedBy { it.orderIndex }
            NetworkResult.Success(topics)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch topics: ${e.message}")
        }
    }

    /**
     * Fetches a single topic by its ID.
     */
    suspend fun getTopicById(topicId: String): NetworkResult<Topic> {
        return try {
            val doc = db.collection(Constants.COLLECTION_TOPICS)
                .document(topicId)
                .get()
                .await()
            val topic = doc.toObject(Topic::class.java)
                ?: return NetworkResult.Error("Topic not found")
            NetworkResult.Success(topic.copy(topicId = doc.id))
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch topic: ${e.message}")
        }
    }

    /**
     * Fetches subtopics directly from the embedded array inside the Topic document.
     */
    suspend fun getSubtopics(topicId: String): NetworkResult<List<Subtopic>> {
        return try {
            val doc = db.collection(Constants.COLLECTION_TOPICS)
                .document(topicId)
                .get()
                .await()
            val topic = doc.toObject(Topic::class.java)
                ?: return NetworkResult.Error("Topic not found")
            NetworkResult.Success(topic.subtopics)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch subtopics: ${e.message}")
        }
    }

    // ─── Videos ───────────────────────────────────────────────────────────────

    /**
     * Fetches all published videos ordered in Kotlin.
     */
    suspend fun getAllVideos(): NetworkResult<List<Video>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_VIDEOS)
                .get()
                .await()
            val videos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Video::class.java)?.copy(videoId = doc.id)
            }.filter { it.isPublished }
                .sortedBy { it.orderIndex }
            NetworkResult.Success(videos)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch videos: ${e.message}")
        }
    }

    /**
     * Fetches videos filtered by topic and sorts them safely in Kotlin.
     */
    suspend fun getVideosByTopic(topicId: String): NetworkResult<List<Video>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_VIDEOS)
                .whereEqualTo("topicId", topicId)
                .get()
                .await()
            val videos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Video::class.java)?.copy(videoId = doc.id)
            }.filter { it.isPublished }
                .sortedBy { it.orderIndex }
            NetworkResult.Success(videos)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch videos: ${e.message}")
        }
    }

    // ─── Questions ────────────────────────────────────────────────────────────

    /**
     * Fetches quiz questions for a given topic and sorts them in Kotlin.
     */
    suspend fun getQuestions(topicId: String): NetworkResult<List<Question>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_QUESTIONS)
                .whereEqualTo("topicId", topicId)
                .get()
                .await()
            val questions = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Question::class.java)?.copy(questionId = doc.id)
            }.sortedBy { it.orderIndex }
            NetworkResult.Success(questions)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch questions: ${e.message}")
        }
    }

    // ─── User Progress ────────────────────────────────────────────────────────

    /**
     * Fetches all progress entries for a user.
     */
    suspend fun getUserProgress(userId: String): NetworkResult<List<UserProgress>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.SUBCOLLECTION_PROGRESS)
                .get()
                .await()
            val progress = snapshot.documents.mapNotNull { doc ->
                doc.toObject(UserProgress::class.java)
            }
            NetworkResult.Success(progress)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch progress: ${e.message}")
        }
    }

    /**
     * Saves or updates a user's progress for a specific topic.
     */
    suspend fun updateProgress(userId: String, progress: UserProgress): NetworkResult<Unit> {
        return try {
            db.collection(Constants.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.SUBCOLLECTION_PROGRESS)
                .document(progress.topicId)
                .set(progress)
                .await()
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to update progress: ${e.message}")
        }
    }

    // ─── Quiz Results ─────────────────────────────────────────────────────────

    /**
     * Saves a quiz result to the user's quizResults subcollection.
     */
    suspend fun saveQuizResult(userId: String, result: QuizResult): NetworkResult<Unit> {
        return try {
            val docRef = db.collection(Constants.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.SUBCOLLECTION_QUIZ_RESULTS)
                .document()
            docRef.set(result.copy(resultId = docRef.id)).await()
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to save quiz result: ${e.message}")
        }
    }

    /**
     * Fetches all quiz results for a user.
     */
    suspend fun getQuizResults(userId: String): NetworkResult<List<QuizResult>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_USERS)
                .document(userId)
                .collection(Constants.SUBCOLLECTION_QUIZ_RESULTS)
                .orderBy("takenAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val results = snapshot.documents.mapNotNull { doc ->
                doc.toObject(QuizResult::class.java)
            }
            NetworkResult.Success(results)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch quiz results: ${e.message}")
        }
    }

    // ─── Leaderboard ─────────────────────────────────────────────────────────

    /**
     * Fetches the all-time top 50 leaderboard entries.
     */
    suspend fun getLeaderboard(): NetworkResult<List<LeaderboardEntry>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_LEADERBOARD)
                .orderBy("totalPoints", Query.Direction.DESCENDING)
                .limit(Constants.LEADERBOARD_LIMIT)
                .get()
                .await()
            val entries = snapshot.documents.mapNotNull { doc ->
                doc.toObject(LeaderboardEntry::class.java)?.copy(userId = doc.id)
            }
            val ranked = entries.mapIndexed { index, entry -> entry.copy(rank = index + 1) }
            NetworkResult.Success(ranked)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch leaderboard: ${e.message}")
        }
    }

    /**
     * Fetches the weekly top 50 leaderboard entries.
     */
    suspend fun getWeeklyLeaderboard(): NetworkResult<List<LeaderboardEntry>> {
        return try {
            val snapshot = db.collection(Constants.COLLECTION_LEADERBOARD)
                .orderBy("weeklyPoints", Query.Direction.DESCENDING)
                .limit(Constants.LEADERBOARD_LIMIT)
                .get()
                .await()
            val entries = snapshot.documents.mapNotNull { doc ->
                doc.toObject(LeaderboardEntry::class.java)?.copy(userId = doc.id)
            }
            val ranked = entries.mapIndexed { index, entry -> entry.copy(rank = index + 1) }
            NetworkResult.Success(ranked)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch weekly leaderboard: ${e.message}")
        }
    }

    /**
     * Updates the leaderboard entry for a user after quiz completion.
     */
    suspend fun updateLeaderboard(
        userId: String,
        displayName: String,
        photoUrl: String,
        pointsEarned: Int,
        quizPassed: Boolean
    ): NetworkResult<Unit> {
        return try {
            val ref = db.collection(Constants.COLLECTION_LEADERBOARD).document(userId)
            db.runTransaction { transaction ->
                val snapshot = transaction.get(ref)
                val currentTotal = snapshot.getLong("totalPoints")?.toInt() ?: 0
                val currentWeekly = snapshot.getLong("weeklyPoints")?.toInt() ?: 0
                val currentQuizzes = snapshot.getLong("quizzesCompleted")?.toInt() ?: 0
                val newTotal = currentTotal + pointsEarned
                transaction.set(ref, mapOf(
                    "userId" to userId,
                    "displayName" to displayName,
                    "photoUrl" to photoUrl,
                    "totalPoints" to newTotal,
                    "weeklyPoints" to (currentWeekly + pointsEarned),
                    "quizzesCompleted" to if (quizPassed) currentQuizzes + 1 else currentQuizzes,
                    "level" to when {
                        newTotal >= Constants.LEVEL_EXPERT_THRESHOLD -> Constants.LEVEL_EXPERT
                        newTotal >= Constants.LEVEL_INTERMEDIATE_THRESHOLD -> Constants.LEVEL_INTERMEDIATE
                        else -> Constants.LEVEL_BEGINNER
                    },
                    "lastUpdated" to Timestamp.now()
                ))
            }.await()
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error("Failed to update leaderboard: ${e.message}")
        }
    }
}