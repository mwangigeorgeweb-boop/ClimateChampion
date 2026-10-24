package com.climatechampion.viewmodel;

/**
 * ViewModel for the quiz screen and quiz result screen.
 * Manages question state, timer, and submission.
 *
 * Flow: select answer -> answer is locked -> user presses Next -> ... -> last Next submits.
 * Pressing Next is allowed after ANY answer (or a timeout), never only after a correct one.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u00104\u001a\u00020\tJ\u000e\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0011J\u0006\u00108\u001a\u000206J\b\u00109\u001a\u000206H\u0014J\b\u0010:\u001a\u000206H\u0002J\u0018\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0011H\u0002J\u000e\u0010>\u001a\u0002062\u0006\u0010=\u001a\u00020\u0011J\b\u0010?\u001a\u000206H\u0002J\b\u0010@\u001a\u000206H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\u00130\u00130\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u000e\u0010 \u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00070\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0017R\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0017R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0017R\u0010\u0010.\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0017R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006B"}, d2 = {"Lcom/climatechampion/viewmodel/QuizViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_answerFeedback", "Landroidx/lifecycle/MutableLiveData;", "Lcom/climatechampion/viewmodel/AnswerFeedback;", "_currentQuestion", "Lcom/climatechampion/model/Question;", "_currentQuestionIndex", "", "kotlin.jvm.PlatformType", "_questions", "Lcom/climatechampion/network/NetworkResult;", "", "_quizResult", "Lcom/climatechampion/model/QuizResponse;", "_selectedAnswer", "", "_timerSeconds", "", "answerFeedback", "Landroidx/lifecycle/LiveData;", "getAnswerFeedback", "()Landroidx/lifecycle/LiveData;", "apiRepo", "Lcom/climatechampion/repository/ApiRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "currentQuestion", "getCurrentQuestion", "currentQuestionIndex", "getCurrentQuestionIndex", "currentTopicId", "firestoreRepo", "Lcom/climatechampion/repository/FirestoreRepository;", "isSubmitting", "", "loadJob", "Lkotlinx/coroutines/Job;", "questionList", "questions", "getQuestions", "quizResult", "getQuizResult", "selectedAnswer", "getSelectedAnswer", "timerJob", "timerSeconds", "getTimerSeconds", "userAnswers", "", "Lcom/climatechampion/model/UserAnswer;", "getTotalQuestions", "loadQuestions", "", "topicId", "nextQuestion", "onCleared", "onTimeUp", "recordAnswer", "question", "answer", "selectAnswer", "startTimer", "submitQuiz", "Companion", "app_debug"})
public final class QuizViewModel extends androidx.lifecycle.ViewModel {
    
    /**
     * Value stored in [selectedAnswer] when the timer runs out.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TIMEOUT = "TIMEOUT";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "QuizViewModel";
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.ApiRepository apiRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.climatechampion.repository.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Question>>> _questions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Question>>> questions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _currentQuestionIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> currentQuestionIndex = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.model.Question> _currentQuestion = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.model.Question> currentQuestion = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Long> _timerSeconds = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Long> timerSeconds = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.climatechampion.model.UserAnswer> userAnswers = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.climatechampion.model.Question> questionList;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentTopicId = "";
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job loadJob;
    private boolean isSubmitting = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _selectedAnswer = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> selectedAnswer = null;
    
    /**
     * Kept for the optional "instant feedback" (practice) mode.
     * The default no-spoiler UI ignores this.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.viewmodel.AnswerFeedback> _answerFeedback = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.viewmodel.AnswerFeedback> answerFeedback = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.QuizResponse>> _quizResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.QuizResponse>> quizResult = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.climatechampion.viewmodel.QuizViewModel.Companion Companion = null;
    
    public QuizViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<java.util.List<com.climatechampion.model.Question>>> getQuestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCurrentQuestionIndex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.model.Question> getCurrentQuestion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Long> getTimerSeconds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSelectedAnswer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.viewmodel.AnswerFeedback> getAnswerFeedback() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.network.NetworkResult<com.climatechampion.model.QuizResponse>> getQuizResult() {
        return null;
    }
    
    public final void loadQuestions(@org.jetbrains.annotations.NotNull()
    java.lang.String topicId) {
    }
    
    private final void startTimer() {
    }
    
    private final void onTimeUp() {
    }
    
    /**
     * Called when the user selects an answer option. The first tap locks the answer.
     */
    public final void selectAnswer(@org.jetbrains.annotations.NotNull()
    java.lang.String answer) {
    }
    
    private final void recordAnswer(com.climatechampion.model.Question question, java.lang.String answer) {
    }
    
    /**
     * Moves to the next question, or submits the quiz after the last one.
     * Ignored until the current question has an answer (prevents double-tap skipping).
     */
    public final void nextQuestion() {
    }
    
    private final void submitQuiz() {
    }
    
    public final int getTotalQuestions() {
        return 0;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/climatechampion/viewmodel/QuizViewModel$Companion;", "", "()V", "TAG", "", "TIMEOUT", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}