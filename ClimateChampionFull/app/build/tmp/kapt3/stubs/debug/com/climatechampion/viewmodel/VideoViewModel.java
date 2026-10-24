package com.climatechampion.viewmodel;

/**
 * ViewModel for video list and video player screens.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\u001eJ\u000e\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u001eJ\u000e\u0010$\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u001eJ\u000e\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u0007R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00050\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R#\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/climatechampion/viewmodel/VideoViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_relatedVideos", "Landroidx/lifecycle/MutableLiveData;", "Lcom/climatechampion/network/NetworkResult;", "", "Lcom/climatechampion/model/Video;", "_selectedVideo", "_videoWatchedResult", "", "_videos", "apiRepo", "Lcom/climatechampion/repository/ApiRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestoreRepo", "Lcom/climatechampion/repository/FirestoreRepository;", "relatedVideos", "Landroidx/lifecycle/LiveData;", "getRelatedVideos", "()Landroidx/lifecycle/LiveData;", "selectedVideo", "getSelectedVideo", "videoWatchedResult", "getVideoWatchedResult", "videos", "getVideos", "watchedVideoIds", "", "", "loadAllVideos", "loadRelatedVideos", "topicId", "loadVideoById", "videoId", "loadVideosByTopic", "onVideoCompleted", "video", "app_debug"})
public final class VideoViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.ApiRepository apiRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> _videos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> videos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.Video>> _selectedVideo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.Video>> selectedVideo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> _relatedVideos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> relatedVideos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<kotlin.Unit>> _videoWatchedResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<kotlin.Unit>> videoWatchedResult = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> watchedVideoIds = null;
    
    public VideoViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> getVideos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.Video>> getSelectedVideo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Video>>> getRelatedVideos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<kotlin.Unit>> getVideoWatchedResult() {
        return null;
    }
    
    public final void loadAllVideos() {
    }
    
    public final void loadVideosByTopic(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId) {
    }
    
    public final void loadVideoById(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId) {
    }
    
    public final void loadRelatedVideos(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId) {
    }
    
    /**
     * Called when a video reaches the end. Awards points once per video per session.
     */
    public final void onVideoCompleted(@org.jetbrains.annotations.NotNull()
    com.climatechampion.model.Video video) {
    }
}