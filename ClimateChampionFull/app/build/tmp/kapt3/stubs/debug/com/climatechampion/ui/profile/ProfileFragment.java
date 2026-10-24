package com.climatechampion.ui.profile;

/**
 * Profile screen showing user stats, level progress, and logout.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\u0012\u0010\u0018\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0016H\u0002J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0014H\u0016J\u001a\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010%\u001a\u00020\u0014H\u0002J\b\u0010&\u001a\u00020\u0014H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\'"}, d2 = {"Lcom/climatechampion/ui/profile/ProfileFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/climatechampion/databinding/FragmentProfileBinding;", "authViewModel", "Lcom/climatechampion/viewmodel/AuthViewModel;", "getAuthViewModel", "()Lcom/climatechampion/viewmodel/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "binding", "getBinding", "()Lcom/climatechampion/databinding/FragmentProfileBinding;", "profileViewModel", "Lcom/climatechampion/viewmodel/ProfileViewModel;", "getProfileViewModel", "()Lcom/climatechampion/viewmodel/ProfileViewModel;", "profileViewModel$delegate", "bindIdentity", "", "fallbackName", "", "fallbackEmail", "championNameFromEmail", "email", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupLogout", "setupObservers", "app_debug"})
public final class ProfileFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.climatechampion.databinding.FragmentProfileBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy profileViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authViewModel$delegate = null;
    
    public ProfileFragment() {
        super();
    }
    
    private final com.climatechampion.databinding.FragmentProfileBinding getBinding() {
        return null;
    }
    
    private final com.climatechampion.viewmodel.ProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.climatechampion.viewmodel.AuthViewModel getAuthViewModel() {
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
    
    /**
     * Shows the signed-in user's name and email straight from Firebase Auth, so they appear
     * even if the Firestore profile document is missing or fails to load.
     */
    private final void bindIdentity(java.lang.String fallbackName, java.lang.String fallbackEmail) {
    }
    
    /**
     * "john.doe@gmail.com" -> "John", "jsmith123@gmail.com" -> "Jsmith", no email -> "Champion".
     */
    private final java.lang.String championNameFromEmail(java.lang.String email) {
        return null;
    }
    
    private final void setupObservers() {
    }
    
    private final void setupLogout() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}