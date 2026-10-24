package com.climatechampion.viewmodel;

/**
 * ViewModel for the profile screen.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0018\u001a\u00020\u0019R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013\u00a8\u0006\u001a"}, d2 = {"Lcom/climatechampion/viewmodel/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_progress", "Landroidx/lifecycle/MutableLiveData;", "Lcom/climatechampion/network/NetworkResult;", "", "Lcom/climatechampion/model/UserProgress;", "_quizResults", "Lcom/climatechampion/model/QuizResult;", "_user", "Lcom/climatechampion/model/User;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestoreRepo", "Lcom/climatechampion/repository/FirestoreRepository;", "progress", "Landroidx/lifecycle/LiveData;", "getProgress", "()Landroidx/lifecycle/LiveData;", "quizResults", "getQuizResults", "user", "getUser", "loadProfile", "", "app_debug"})
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.User>> _user = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.User>> user = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> _progress = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> progress = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.QuizResult>>> _quizResults = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.QuizResult>>> quizResults = null;
    
    public ProfileViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.User>> getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.UserProgress>>> getProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.QuizResult>>> getQuizResults() {
        return null;
    }
    
    public final void loadProfile() {
    }
}