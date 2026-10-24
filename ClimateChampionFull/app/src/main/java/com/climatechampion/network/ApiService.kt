package com.climatechampion.network

import com.climatechampion.model.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit service interface defining all REST API endpoints for Climate Champion.
 * Base URL is configured in [ApiClient].
 */
interface ClimateApiService {

    // ─── Topics ──────────────────────────────────────────────────────────────

    @GET("topics")
    suspend fun getAllTopics(): Response<List<Topic>>

    @GET("topics/{id}")
    suspend fun getTopicById(@Path("id") topicId: String): Response<Topic>

    @GET("topics/{id}/subtopics")
    suspend fun getSubtopics(
        @Path("id") topicId: String
    ): Response<List<Subtopic>>

    @GET("topics/{id}/questions")
    suspend fun getQuestions(
        @Path("id") topicId: String
    ): Response<List<Question>>

    // ─── Videos ──────────────────────────────────────────────────────────────

    @GET("videos")
    suspend fun getAllVideos(): Response<List<Video>>

    @GET("videos/{id}")
    suspend fun getVideoById(@Path("id") videoId: String): Response<Video>

    @GET("videos/topic/{topicId}")
    suspend fun getVideosByTopic(
        @Path("topicId") topicId: String
    ): Response<List<Video>>

    // ─── Leaderboard ─────────────────────────────────────────────────────────

    @GET("leaderboard")
    suspend fun getLeaderboard(): Response<List<LeaderboardEntry>>

    @GET("leaderboard/weekly")
    suspend fun getWeeklyLeaderboard(): Response<List<LeaderboardEntry>>

    // ─── User Progress ────────────────────────────────────────────────────────

    @GET("user/{userId}/progress")
    suspend fun getUserProgress(
        @Path("userId") userId: String
    ): Response<List<UserProgress>>

    // ─── POST Actions ─────────────────────────────────────────────────────────

    @POST("quiz/submit")
    suspend fun submitQuiz(
        @Body submission: QuizSubmission
    ): Response<QuizResponse>

    @POST("user/progress")
    suspend fun updateProgress(
        @Body progress: UserProgress
    ): Response<Void>

    @POST("user/video/watched")
    suspend fun markVideoWatched(
        @Body body: Map<String, String>
    ): Response<Void>
}
