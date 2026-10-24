package com.climatechampion.ui.quiz;

/**
 * Quiz screen — one question at a time with a timer.
 *
 * Default (assessment) mode: tapping an option locks it in and highlights it neutrally.
 * No "correct / wrong" is shown and the answer is never revealed during the quiz.
 * The Next / Finish button appears after ANY answer (or a timeout).
 * Results are shown only on the result screen.
 *
 * Set INSTANT_FEEDBACK = true for practice mode (shows correct/incorrect + explanation).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u001aH\u0016J\u001a\u0010&\u001a\u00020\u001a2\u0006\u0010\'\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\b\u0010)\u001a\u00020\u001aH\u0002J\b\u0010*\u001a\u00020\u001aH\u0002J\b\u0010+\u001a\u00020\u001aH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006-"}, d2 = {"Lcom/climatechampion/ui/quiz/QuizFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/climatechampion/databinding/FragmentQuizBinding;", "args", "Lcom/climatechampion/ui/quiz/QuizFragmentArgs;", "getArgs", "()Lcom/climatechampion/ui/quiz/QuizFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Lcom/climatechampion/databinding/FragmentQuizBinding;", "currentIsTrueFalse", "", "viewModel", "Lcom/climatechampion/viewmodel/QuizViewModel;", "getViewModel", "()Lcom/climatechampion/viewmodel/QuizViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "allAnswerButtons", "", "Lcom/google/android/material/button/MaterialButton;", "lockAnswerButtons", "", "answer", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "optionButtons", "resetAnswerButtons", "setupClickListeners", "setupObservers", "Companion", "app_debug"})
@kotlin.Suppress(names = {"KotlinConstantConditions", "SimplifyBooleanWithConstants"})
public final class QuizFragment extends androidx.fragment.app.Fragment {
    private static final boolean INSTANT_FEEDBACK = false;
    @org.jetbrains.annotations.Nullable()
    private com.climatechampion.databinding.FragmentQuizBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean currentIsTrueFalse = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.climatechampion.ui.quiz.QuizFragment.Companion Companion = null;
    
    public QuizFragment() {
        super();
    }
    
    private final com.climatechampion.databinding.FragmentQuizBinding getBinding() {
        return null;
    }
    
    private final com.climatechampion.ui.quiz.QuizFragmentArgs getArgs() {
        return null;
    }
    
    private final com.climatechampion.viewmodel.QuizViewModel getViewModel() {
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
    
    private final void setupClickListeners() {
    }
    
    private final void setupObservers() {
    }
    
    private final java.util.List<com.google.android.material.button.MaterialButton> optionButtons() {
        return null;
    }
    
    private final java.util.List<com.google.android.material.button.MaterialButton> allAnswerButtons() {
        return null;
    }
    
    private final void resetAnswerButtons() {
    }
    
    /**
     * Locks every button and marks the chosen one with a neutral color (no right/wrong hint).
     */
    private final void lockAnswerButtons(java.lang.String answer) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/climatechampion/ui/quiz/QuizFragment$Companion;", "", "()V", "INSTANT_FEEDBACK", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}