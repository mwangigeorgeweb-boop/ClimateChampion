package com.climatechampion.ui.video;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u001cH\u0003J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\b\u0010)\u001a\u00020\u0019H\u0016J\b\u0010*\u001a\u00020\u0019H\u0016J\u001a\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020\u0019H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006/"}, d2 = {"Lcom/climatechampion/ui/video/VideoPlayerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/climatechampion/databinding/FragmentVideoPlayerBinding;", "args", "Lcom/climatechampion/ui/video/VideoPlayerFragmentArgs;", "getArgs", "()Lcom/climatechampion/ui/video/VideoPlayerFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Lcom/climatechampion/databinding/FragmentVideoPlayerBinding;", "currentVideo", "Lcom/climatechampion/model/Video;", "relatedAdapter", "Lcom/climatechampion/ui/video/VideoAdapter;", "viewModel", "Lcom/climatechampion/viewmodel/VideoViewModel;", "getViewModel", "()Lcom/climatechampion/viewmodel/VideoViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindVideoDetails", "", "video", "extractYoutubeId", "", "url", "loadYouTubeInWebView", "videoUrl", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "setupObservers", "setupRelatedRecycler", "app_debug"})
public final class VideoPlayerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.climatechampion.databinding.FragmentVideoPlayerBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.climatechampion.model.Video currentVideo;
    private com.climatechampion.ui.video.VideoAdapter relatedAdapter;
    
    public VideoPlayerFragment() {
        super();
    }
    
    private final com.climatechampion.databinding.FragmentVideoPlayerBinding getBinding() {
        return null;
    }
    
    private final com.climatechampion.ui.video.VideoPlayerFragmentArgs getArgs() {
        return null;
    }
    
    private final com.climatechampion.viewmodel.VideoViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRelatedRecycler() {
    }
    
    private final void setupObservers() {
    }
    
    private final void bindVideoDetails(com.climatechampion.model.Video video) {
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    private final void loadYouTubeInWebView(java.lang.String videoUrl) {
    }
    
    private final java.lang.String extractYoutubeId(java.lang.String url) {
        return null;
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}