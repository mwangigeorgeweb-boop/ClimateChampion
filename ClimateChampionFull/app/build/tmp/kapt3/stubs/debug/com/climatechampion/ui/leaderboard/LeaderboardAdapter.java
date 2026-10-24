package com.climatechampion.ui.leaderboard;

/**
 * RecyclerView adapter for the leaderboard screen.
 * Highlights the current user's row and shows rank medals for top 3.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/climatechampion/ui/leaderboard/LeaderboardAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/climatechampion/model/LeaderboardEntry;", "Lcom/climatechampion/ui/leaderboard/LeaderboardAdapter$LeaderboardViewHolder;", "currentUserId", "", "(Ljava/lang/String;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "LeaderboardViewHolder", "app_debug"})
public final class LeaderboardAdapter extends androidx.recyclerview.widget.ListAdapter<com.climatechampion.model.LeaderboardEntry, com.climatechampion.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String currentUserId = null;
    
    public LeaderboardAdapter(@org.jetbrains.annotations.Nullable()
    java.lang.String currentUserId) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.climatechampion.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.climatechampion.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder holder, int position) {
    }
    
    public LeaderboardAdapter() {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/climatechampion/ui/leaderboard/LeaderboardAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/climatechampion/model/LeaderboardEntry;", "()V", "areContentsTheSame", "", "old", "new", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.climatechampion.model.LeaderboardEntry> {
        
        public DiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.LeaderboardEntry old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.LeaderboardEntry p1_54480) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.LeaderboardEntry old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.LeaderboardEntry p1_54480) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/climatechampion/ui/leaderboard/LeaderboardAdapter$LeaderboardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/climatechampion/databinding/ItemLeaderboardBinding;", "(Lcom/climatechampion/databinding/ItemLeaderboardBinding;)V", "bind", "", "entry", "Lcom/climatechampion/model/LeaderboardEntry;", "isCurrentUser", "", "app_debug"})
    public static final class LeaderboardViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.climatechampion.databinding.ItemLeaderboardBinding binding = null;
        
        public LeaderboardViewHolder(@org.jetbrains.annotations.NotNull()
        com.climatechampion.databinding.ItemLeaderboardBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.LeaderboardEntry entry, boolean isCurrentUser) {
        }
    }
}