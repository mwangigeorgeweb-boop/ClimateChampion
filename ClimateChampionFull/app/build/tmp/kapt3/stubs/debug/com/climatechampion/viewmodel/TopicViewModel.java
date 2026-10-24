package com.climatechampion.viewmodel;

/**
 * ViewModel for topic list and topic detail screens.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\u000bJ\u000e\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$J\u0006\u0010&\u001a\u00020\u000bJ\u000e\u0010\'\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$J\u0016\u0010(\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$2\u0006\u0010)\u001a\u00020*R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R#\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00050\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R#\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u00050\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\u00050\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018\u00a8\u0006+"}, d2 = {"Lcom/climatechampion/viewmodel/TopicViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_selectedTopic", "Landroidx/lifecycle/MutableLiveData;", "Lcom/climatechampion/network/NetworkResult;", "Lcom/climatechampion/model/Topic;", "_subtopics", "", "Lcom/climatechampion/model/Subtopic;", "_topicCompleteResult", "", "_topics", "_userProgress", "Lcom/climatechampion/model/UserProgress;", "apiRepo", "Lcom/climatechampion/repository/ApiRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestoreRepo", "Lcom/climatechampion/repository/FirestoreRepository;", "selectedTopic", "Landroidx/lifecycle/LiveData;", "getSelectedTopic", "()Landroidx/lifecycle/LiveData;", "subtopics", "getSubtopics", "topicCompleteResult", "getTopicCompleteResult", "topics", "getTopics", "userProgress", "getUserProgress", "loadAllTopics", "loadSubtopics", "topicId", "", "loadTopicById", "loadUserProgress", "markTopicComplete", "updateProgress", "percentComplete", "", "app_debug"})
public final class TopicViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.ApiRepository apiRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Topic>>> _topics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Topic>>> topics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.Topic>> _selectedTopic = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.Topic>> selectedTopic = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Subtopic>>> _subtopics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Subtopic>>> subtopics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> _userProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> userProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<kotlin.Unit>> _topicCompleteResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<kotlin.Unit>> topicCompleteResult = null;
    
    public TopicViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Topic>>> getTopics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.Topic>> getSelectedTopic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Subtopic>>> getSubtopics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> getUserProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<kotlin.Unit>> getTopicCompleteResult() {
        return null;
    }
    
    public final void loadAllTopics() {
    }
    
    public final void loadTopicById(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId) {
    }
    
    public final void loadSubtopics(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId) {
    }
    
    public final void loadUserProgress() {
    }
    
    /**
     * Marks a topic as complete, saves progress, and awards points.
     */
    public final void markTopicComplete(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId) {
    }
    
    /**
     * Updates partial progress for a topic (e.g., reading a subtopic).
     */
    public final void updateProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId, int percentComplete) {
    }
}