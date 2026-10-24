package com.climatechampion.viewmodel;

/**
 * ViewModel for authentication screens (Login, Register, Splash).
 * Handles Firebase Auth operations and exposes state via LiveData.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010J\u0006\u0010\u001d\u001a\u00020\u001aJ\u001e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010J\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000e\u00a8\u0006#"}, d2 = {"Lcom/climatechampion/viewmodel/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_authState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/climatechampion/network/NetworkResult;", "Lcom/google/firebase/auth/FirebaseUser;", "_logoutState", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "authState", "Landroidx/lifecycle/LiveData;", "getAuthState", "()Landroidx/lifecycle/LiveData;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "firestoreRepo", "Lcom/climatechampion/repository/FirestoreRepository;", "isLoggedIn", "()Z", "logoutState", "getLogoutState", "login", "", "email", "password", "logout", "register", "displayName", "signInWithCredential", "credential", "Lcom/google/firebase/auth/AuthCredential;", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<com.google.firebase.auth.FirebaseUser>> _authState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.google.firebase.auth.FirebaseUser>> authState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _logoutState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> logoutState = null;
    
    public AuthViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.google.firebase.auth.FirebaseUser>> getAuthState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLogoutState() {
        return null;
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    /**
     * Registers a new user with email and password, then creates their Firestore profile.
     */
    public final void register(@org.jetbrains.annotations.NotNull()
    java.lang.String displayName, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    /**
     * Signs in an existing user with email and password.
     */
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    /**
     * Signs in with a Google credential obtained from Google Sign-In flow.
     */
    public final void signInWithCredential(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential credential) {
    }
    
    /**
     * Signs the current user out.
     */
    public final void logout() {
    }
}