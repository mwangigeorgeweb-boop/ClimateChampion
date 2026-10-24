package com.climatechampion.network;

/**
 * Retrofit service interface defining all REST API endpoints for Climate Champion.
 * Base URL is configured in [ApiClient].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J$\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00040\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00040\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J*\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\u0014\b\u0001\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00032\b\b\u0001\u0010!\u001a\u00020\"H\u00a7@\u00a2\u0006\u0002\u0010#J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\b\b\u0001\u0010%\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010&\u00a8\u0006\'"}, d2 = {"Lcom/climatechampion/network/ClimateApiService;", "", "getAllTopics", "Lretrofit2/Response;", "", "Lcom/climatechampion/model/Topic;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllVideos", "Lcom/climatechampion/model/Video;", "getLeaderboard", "Lcom/climatechampion/model/LeaderboardEntry;", "getQuestions", "Lcom/climatechampion/model/Question;", "topicId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubtopics", "Lcom/climatechampion/model/Subtopic;", "getTopicById", "getUserProgress", "Lcom/climatechampion/model/UserProgress;", "userId", "getVideoById", "videoId", "getVideosByTopic", "getWeeklyLeaderboard", "markVideoWatched", "Ljava/lang/Void;", "body", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitQuiz", "Lcom/climatechampion/model/QuizResponse;", "submission", "Lcom/climatechampion/model/QuizSubmission;", "(Lcom/climatechampion/model/QuizSubmission;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "progress", "(Lcom/climatechampion/model/UserProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ClimateApiService {
    
    @retrofit2.http.GET(value = "topics")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllTopics(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.Topic>>> $completion);
    
    @retrofit2.http.GET(value = "topics/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTopicById(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.climatechampion.model.Topic>> $completion);
    
    @retrofit2.http.GET(value = "topics/{id}/subtopics")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSubtopics(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.Subtopic>>> $completion);
    
    @retrofit2.http.GET(value = "topics/{id}/questions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getQuestions(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.Question>>> $completion);
    
    @retrofit2.http.GET(value = "videos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllVideos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.Video>>> $completion);
    
    @retrofit2.http.GET(value = "videos/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVideoById(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.climatechampion.model.Video>> $completion);
    
    @retrofit2.http.GET(value = "videos/topic/{topicId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVideosByTopic(@retrofit2.http.Path(value = "topicId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.Video>>> $completion);
    
    @retrofit2.http.GET(value = "leaderboard")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLeaderboard(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.LeaderboardEntry>>> $completion);
    
    @retrofit2.http.GET(value = "leaderboard/weekly")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWeeklyLeaderboard(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.LeaderboardEntry>>> $completion);
    
    @retrofit2.http.GET(value = "user/{userId}/progress")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserProgress(@retrofit2.http.Path(value = "userId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.climatechampion.model.UserProgress>>> $completion);
    
    @retrofit2.http.POST(value = "quiz/submit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitQuiz(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.climatechampion.model.QuizSubmission submission, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.climatechampion.model.QuizResponse>> $completion);
    
    @retrofit2.http.POST(value = "user/progress")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProgress(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.climatechampion.model.UserProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> $completion);
    
    @retrofit2.http.POST(value = "user/video/watched")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markVideoWatched(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> $completion);
}