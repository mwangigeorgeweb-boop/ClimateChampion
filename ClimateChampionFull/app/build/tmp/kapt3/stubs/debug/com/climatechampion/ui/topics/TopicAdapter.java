package com.climatechampion.ui.topics;

/**
 * RecyclerView adapter for the topic list screen.
 * Shows topic card with title, category, difficulty, progress, and thumbnail image.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0017\u0018B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\f\u001a\u00020\u00062\n\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u0014\u0010\u0014\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/climatechampion/ui/topics/TopicAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/climatechampion/model/Topic;", "Lcom/climatechampion/ui/topics/TopicAdapter$TopicViewHolder;", "onTopicClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "progressMap", "", "", "Lcom/climatechampion/model/UserProgress;", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setProgress", "progressList", "", "TopicDiffCallback", "TopicViewHolder", "app_debug"})
public final class TopicAdapter extends androidx.recyclerview.widget.ListAdapter<com.climatechampion.model.Topic, com.climatechampion.ui.topics.TopicAdapter.TopicViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.climatechampion.model.Topic, kotlin.Unit> onTopicClick = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.climatechampion.model.UserProgress> progressMap = null;
    
    public TopicAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.climatechampion.model.Topic, kotlin.Unit> onTopicClick) {
        super(null);
    }
    
    public final void setProgress(@org.jetbrains.annotations.NotNull()
    java.util.List<com.climatechampion.model.UserProgress> progressList) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.climatechampion.ui.topics.TopicAdapter.TopicViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.climatechampion.ui.topics.TopicAdapter.TopicViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/climatechampion/ui/topics/TopicAdapter$TopicDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/climatechampion/model/Topic;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class TopicDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.climatechampion.model.Topic> {
        
        public TopicDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Topic oldItem, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Topic newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Topic oldItem, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Topic newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/climatechampion/ui/topics/TopicAdapter$TopicViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/climatechampion/databinding/ItemTopicBinding;", "(Lcom/climatechampion/ui/topics/TopicAdapter;Lcom/climatechampion/databinding/ItemTopicBinding;)V", "bind", "", "topic", "Lcom/climatechampion/model/Topic;", "progress", "Lcom/climatechampion/model/UserProgress;", "app_debug"})
    public final class TopicViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.climatechampion.databinding.ItemTopicBinding binding = null;
        
        public TopicViewHolder(@org.jetbrains.annotations.NotNull()
        com.climatechampion.databinding.ItemTopicBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Topic topic, @org.jetbrains.annotations.Nullable()
        com.climatechampion.model.UserProgress progress) {
        }
    }
}