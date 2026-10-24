package com.climatechampion.repository;

/**
 * Repository for all REST API calls via Retrofit.
 * Falls back to Firestore via [FirestoreRepository] when needed.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\bH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\bH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\"\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\t0\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\"\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\bH\u0086@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010\u001fJ$\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\b2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010$J\u001c\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001b0\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010&J$\u0010\'\u001a\b\u0012\u0004\u0012\u00020!0\b2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010*R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/climatechampion/repository/ApiRepository;", "", "firestoreRepository", "Lcom/climatechampion/repository/FirestoreRepository;", "(Lcom/climatechampion/repository/FirestoreRepository;)V", "api", "Lcom/climatechampion/network/ClimateApiService;", "getAllTopics", "Lcom/climatechampion/network/NetworkResult;", "", "Lcom/climatechampion/model/Topic;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllVideos", "Lcom/climatechampion/model/Video;", "getLeaderboard", "Lcom/climatechampion/model/LeaderboardEntry;", "getQuestions", "Lcom/climatechampion/model/Question;", "topicId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubtopics", "Lcom/climatechampion/model/Subtopic;", "getTopicById", "getVideosByTopic", "getWeeklyLeaderboard", "gradeLocally", "Lcom/climatechampion/model/QuizResponse;", "submission", "Lcom/climatechampion/model/QuizSubmission;", "reason", "(Lcom/climatechampion/model/QuizSubmission;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markVideoWatched", "", "userId", "videoId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitQuiz", "(Lcom/climatechampion/model/QuizSubmission;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "progress", "Lcom/climatechampion/model/UserProgress;", "(Ljava/lang/String;Lcom/climatechampion/model/UserProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ApiRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.FirestoreRepository firestoreRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.network.ClimateApiService api = null;
    
    public ApiRepository(@org.jetbrains.annotations.NotNull()
    com.climatechampion.repository.FirestoreRepository firestoreRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllTopics(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Topic>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTopicById(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<com.climatechampion.model.Topic>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSubtopics(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Subtopic>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuestions(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Question>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllVideos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getVideosByTopic(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLeaderboard(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWeeklyLeaderboard(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> $completion) {
        return null;
    }
    
    /**
     * Submits quiz answers to the REST API and returns the graded response.
     * If the API is unreachable (e.g. Cloud Functions not deployed / BASE_URL is localhost)
     * or returns an error, the quiz is graded on the device using the questions in Firestore.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object submitQuiz(@org.jetbrains.annotations.NotNull()
    com.climatechampion.model.QuizSubmission submission, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<com.climatechampion.model.QuizResponse>> $completion) {
        return null;
    }
    
    /**
     * Local grading fallback. Mirrors the server rules from the README:
     * pass = 70% or more (+100 points), perfect score = +200 points.
     * An empty answer (timeout) counts as wrong.
     */
    private final java.lang.Object gradeLocally(com.climatechampion.model.QuizSubmission submission, java.lang.String reason, kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<com.climatechampion.model.QuizResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.climatechampion.model.UserProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markVideoWatched(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    public ApiRepository() {
        super();
    }
}