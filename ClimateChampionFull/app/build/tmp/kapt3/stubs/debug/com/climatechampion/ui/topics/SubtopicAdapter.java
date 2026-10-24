package com.climatechampion.ui.topics;

/**
 * RecyclerView adapter for subtopics shown in the topic detail screen.
 * Each row is expandable to show full content or summary.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/climatechampion/ui/topics/SubtopicAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/climatechampion/model/Subtopic;", "Lcom/climatechampion/ui/topics/SubtopicAdapter$SubtopicViewHolder;", "()V", "expandedPositions", "", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "SubtopicViewHolder", "app_debug"})
public final class SubtopicAdapter extends androidx.recyclerview.widget.ListAdapter<com.climatechampion.model.Subtopic, com.climatechampion.ui.topics.SubtopicAdapter.SubtopicViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> expandedPositions = null;
    
    public SubtopicAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.climatechampion.ui.topics.SubtopicAdapter.SubtopicViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.climatechampion.ui.topics.SubtopicAdapter.SubtopicViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/climatechampion/ui/topics/SubtopicAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/climatechampion/model/Subtopic;", "()V", "areContentsTheSame", "", "old", "new", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.climatechampion.model.Subtopic> {
        
        public DiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Subtopic old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Subtopic p1_54480) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Subtopic old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Subtopic p1_54480) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/climatechampion/ui/topics/SubtopicAdapter$SubtopicViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/climatechampion/databinding/ItemSubtopicBinding;", "(Lcom/climatechampion/ui/topics/SubtopicAdapter;Lcom/climatechampion/databinding/ItemSubtopicBinding;)V", "bind", "", "subtopic", "Lcom/climatechampion/model/Subtopic;", "isExpanded", "", "app_debug"})
    public final class SubtopicViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.climatechampion.databinding.ItemSubtopicBinding binding = null;
        
        public SubtopicViewHolder(@org.jetbrains.annotations.NotNull()
        com.climatechampion.databinding.ItemSubtopicBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.Subtopic subtopic, boolean isExpanded) {
        }
    }
}