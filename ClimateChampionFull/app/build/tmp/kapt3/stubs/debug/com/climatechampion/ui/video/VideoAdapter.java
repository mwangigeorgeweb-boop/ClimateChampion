package com.climatechampion.ui.video;

/**
 * RecyclerView adapter for the video list screen (grid or horizontal strip).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0015\u0016B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\nJ\u001c\u0010\r\u001a\u00020\u00062\n\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/climatechampion/ui/video/VideoAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/climatechampion/model/Video;", "Lcom/climatechampion/ui/video/VideoAdapter$VideoViewHolder;", "onVideoClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "watchedVideoIds", "", "", "markWatched", "videoId", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "VideoDiffCallback", "VideoViewHolder", "app_debug"})
public final class VideoAdapter extends androidx.recyclerview.widget.ListAdapter<com.climatechampion.model.Video, com.climatechampion.ui.video.VideoAdapter.VideoViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.climatechampion.model.Video, kotlin.Unit> onVideoClick = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> watchedVideoIds = null;
    
    public VideoAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.climatechampion.model.Video, kotlin.Unit> onVideoClick) {
        super(null);
    }
    
    public final void markWatched(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.climatechampion.ui.video.VideoAdapter.VideoViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.climatechampion.ui.video.VideoAdapter.VideoViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/climatechampion/ui/video/VideoAdapter$VideoDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/climatechampion/model/Video;", "()V", "areContentsTheSame", "", "old", "new", "areItemsTheSame", "app_debug"})
    public static final class VideoDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.climatechampion.model.Video> {
        
        public VideoDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Video old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Video p1_54480) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Video old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Video p1_54480) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/climatechampion/ui/video/VideoAdapter$VideoViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/climatechampion/databinding/ItemVideoBinding;", "(Lcom/climatechampion/ui/video/VideoAdapter;Lcom/climatechampion/databinding/ItemVideoBinding;)V", "bind", "", "video", "Lcom/climatechampion/model/Video;", "isWatched", "", "app_debug"})
    public final class VideoViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.climatechampion.databinding.ItemVideoBinding binding = null;
        
        public VideoViewHolder(@org.jetbrains.annotations.NotNull()
        com.climatechampion.databinding.ItemVideoBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Video video, boolean isWatched) {
        }
    }
}