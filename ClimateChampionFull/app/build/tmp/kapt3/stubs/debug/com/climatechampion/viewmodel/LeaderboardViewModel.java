package com.climatechampion.viewmodel;

/**
 * ViewModel for the leaderboard screen.
 * Supports toggling between all-time and weekly views.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001b\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u0019R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0015R#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015\u00a8\u0006\u001d"}, d2 = {"Lcom/climatechampion/viewmodel/LeaderboardViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_isWeeklyMode", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_leaderboard", "Lcom/climatechampion/network/NetworkResult;", "", "Lcom/climatechampion/model/LeaderboardEntry;", "apiRepo", "Lcom/climatechampion/repository/ApiRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "isWeeklyMode", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "leaderboard", "getLeaderboard", "loadAllTimeLeaderboard", "", "loadWeeklyLeaderboard", "refresh", "toggleMode", "app_debug"})
public final class LeaderboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.ApiRepository apiRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> _leaderboard = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> leaderboard = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isWeeklyMode = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isWeeklyMode = null;
    
    public LeaderboardViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.LeaderboardEntry>>> getLeaderboard() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isWeeklyMode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    public final void loadAllTimeLeaderboard() {
    }
    
    public final void loadWeeklyLeaderboard() {
    }
    
    public final void toggleMode() {
    }
    
    public final void refresh() {
    }
}