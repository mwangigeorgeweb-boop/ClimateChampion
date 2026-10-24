package com.climatechampion.repository;

/**
 * Repository for all direct Firestore database operations.
 * Wraps results in [NetworkResult] for consistent UI state handling.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00120\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00120\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0014J\"\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00120\u00062\u0006\u0010\u001b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\"\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00120\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\"\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00120\u00062\u0006\u0010\u001b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\u001b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\"\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00120\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\"\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00120\u00062\u0006\u0010\u001b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00120\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0014J$\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010)J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001cJ<\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u000200H\u0086@\u00a2\u0006\u0002\u00101J$\u00102\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u00103\u001a\u00020$H\u0086@\u00a2\u0006\u0002\u00104R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/climatechampion/repository/FirestoreRepository;", "", "()V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "addPoints", "Lcom/climatechampion/network/NetworkResult;", "", "userId", "", "pointsToAdd", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createOrUpdateUser", "user", "Lcom/climatechampion/model/User;", "(Lcom/climatechampion/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTopics", "", "Lcom/climatechampion/model/Topic;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllVideos", "Lcom/climatechampion/model/Video;", "getLeaderboard", "Lcom/climatechampion/model/LeaderboardEntry;", "getQuestions", "Lcom/climatechampion/model/Question;", "topicId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQuizResults", "Lcom/climatechampion/model/QuizResult;", "getSubtopics", "Lcom/climatechampion/model/Subtopic;", "getTopicById", "getUser", "getUserProgress", "Lcom/climatechampion/model/UserProgress;", "getVideosByTopic", "getWeeklyLeaderboard", "saveQuizResult", "result", "(Ljava/lang/String;Lcom/climatechampion/model/QuizResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLastActive", "updateLeaderboard", "displayName", "photoUrl", "pointsEarned", "quizPassed", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "progress", "(Ljava/lang/String;Lcom/climatechampion/model/UserProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class FirestoreRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    
    public FirestoreRepository() {
        super();
    }
    
    /**
     * Creates or updates a user document in Firestore.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createOrUpdateUser(@org.jetbrains.annotations.NotNull()
    com.climatechampion.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Fetches a single user document by userId.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<com.climatechampion.model.User>> $completion) {
        return null;
    }
    
    /**
     * Updates a user's total points and recalculates their level.
     * If the user's profile document doesn't exist yet it is created, because
     * transaction.update() on a missing document fails and the points were silently lost.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addPoints(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int pointsToAdd, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Updates the lastActive timestamp for daily login tracking.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLastActive(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Fetches all published topics and sorts them in Kotlin to bypass missing composite indexes.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllTopics(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Topic>>> $completion) {
        return null;
    }
    
    /**
     * Fetches a single topic by its ID.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTopicById(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<com.climatechampion.model.Topic>> $completion) {
        return null;
    }
    
    /**
     * Fetches subtopics directly from the embedded array inside the Topic document.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSubtopics(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Subtopic>>> $completion) {
        return null;
    }
    
    /**
     * Fetches all published videos ordered in Kotlin.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllVideos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> $completion) {
        return null;
    }
    
    /**
     * Fetches videos filtered by topic and sorts them safely in Kotlin.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getVideosByTopic(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> $completion) {
        return null;
    }
    
    /**
     * Fetches quiz questions for a given topic and sorts them in Kotlin.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuestions(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Question>>> $completion) {
        return null;
    }
    
    /**
     * Fetches all progress entries for a user.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> $completion) {
        return null;
    }
    
    /**
     * Saves or updates a user's progress for a specific topic.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.climatechampion.model.UserProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Saves a quiz result to the user's quizResults subcollection.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveQuizResult(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.climatechampion.model.QuizResult result, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Fetches all quiz results for a user.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuizResults(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.QuizResult>>> $completion) {
        return null;
    }
    
    /**
     * Fetches the all-time top 50 leaderboard entries.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLeaderboard(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> $completion) {
        return null;
    }
    
    /**
     * Fetches the weekly top 50 leaderboard entries.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWeeklyLeaderboard(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> $completion) {
        return null;
    }
    
    /**
     * Updates the leaderboard entry for a user after quiz completion.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLeaderboard(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String displayName, @org.jetbrains.annotations.NotNull()
    java.lang.String photoUrl, int pointsEarned, boolean quizPassed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.climatechampion.network.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
}