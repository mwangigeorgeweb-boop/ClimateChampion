package com.climatechampion.ui.calculator;

/**
 * RecyclerView adapter for the personalised carbon reduction tips list.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/climatechampion/ui/calculator/CarbonTipsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/climatechampion/model/CarbonTip;", "Lcom/climatechampion/ui/calculator/CarbonTipsAdapter$TipViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "TipDiffCallback", "TipViewHolder", "app_debug"})
public final class CarbonTipsAdapter extends androidx.recyclerview.widget.ListAdapter<com.climatechampion.model.CarbonTip, com.climatechampion.ui.calculator.CarbonTipsAdapter.TipViewHolder> {
    
    public CarbonTipsAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.climatechampion.ui.calculator.CarbonTipsAdapter.TipViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.climatechampion.ui.calculator.CarbonTipsAdapter.TipViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/climatechampion/ui/calculator/CarbonTipsAdapter$TipDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/climatechampion/model/CarbonTip;", "()V", "areContentsTheSame", "", "old", "new", "areItemsTheSame", "app_debug"})
    public static final class TipDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.climatechampion.model.CarbonTip> {
        
        public TipDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.CarbonTip old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.CarbonTip p1_54480) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.CarbonTip old, @org.jetbrains.annotations.NotNull()
        com.climatechampion.model.CarbonTip p1_54480) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/climatechampion/ui/calculator/CarbonTipsAdapter$TipViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/climatechampion/databinding/ItemCarbonTipBinding;", "(Lcom/climatechampion/ui/calculator/CarbonTipsAdapter;Lcom/climatechampion/databinding/ItemCarbonTipBinding;)V", "bind", "", "tip", "Lcom/climatechampion/model/CarbonTip;", "app_debug"})
    public final class TipViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.climatechampion.databinding.ItemCarbonTipBinding binding = null;
        
        public TipViewHolder(@org.jetbrains.annotations.NotNull()
        com.climatechampion.databinding.ItemCarbonTipBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.climatechampion.model.CarbonTip tip) {
        }
    }
}