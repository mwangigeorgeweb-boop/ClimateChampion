package com.climatechampion.viewmodel;

/**
 * ViewModel for the Carbon Footprint Calculator.
 * Holds form state across the 3 step pages and exposes the final result.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010G\u001a\u00020HJ\u0006\u0010I\u001a\u00020JJ\u000e\u0010K\u001a\u00020J2\u0006\u0010L\u001a\u00020\u0005J\u0006\u0010M\u001a\u00020JJ\u0006\u0010N\u001a\u00020JJ\u0006\u0010O\u001a\u00020JJ\u0006\u0010P\u001a\u00020JR\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\b0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R\u001a\u0010$\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017R\u001a\u0010\'\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011R\u001a\u0010*\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0015\"\u0004\b,\u0010\u0017R\u001a\u0010-\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\b0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u001a\u00104\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010/\"\u0004\b6\u00101R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\n0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u001a\u00109\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u000f\"\u0004\b;\u0010\u0011R\u001a\u0010<\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0015\"\u0004\b>\u0010\u0017R\u0019\u0010?\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010 R\u001a\u0010A\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010/\"\u0004\bC\u00101R\u001a\u0010D\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010/\"\u0004\bF\u00101\u00a8\u0006Q"}, d2 = {"Lcom/climatechampion/viewmodel/CarbonCalculatorViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_currentStep", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_liveEstimate", "", "_pointsAwarded", "", "_result", "Lcom/climatechampion/model/CarbonResult;", "carKmPerWeek", "getCarKmPerWeek", "()F", "setCarKmPerWeek", "(F)V", "carType", "", "getCarType", "()Ljava/lang/String;", "setCarType", "(Ljava/lang/String;)V", "compostsFood", "getCompostsFood", "()Z", "setCompostsFood", "(Z)V", "currentStep", "Landroidx/lifecycle/LiveData;", "getCurrentStep", "()Landroidx/lifecycle/LiveData;", "electricityKwh", "getElectricityKwh", "setElectricityKwh", "energySource", "getEnergySource", "setEnergySource", "gasM3", "getGasM3", "setGasM3", "heatingType", "getHeatingType", "setHeatingType", "householdSize", "getHouseholdSize", "()I", "setHouseholdSize", "(I)V", "liveEstimate", "getLiveEstimate", "longHaulFlights", "getLongHaulFlights", "setLongHaulFlights", "pointsAwarded", "getPointsAwarded", "publicTransitHours", "getPublicTransitHours", "setPublicTransitHours", "recyclingHabit", "getRecyclingHabit", "setRecyclingHabit", "result", "getResult", "shortHaulFlights", "getShortHaulFlights", "setShortHaulFlights", "wasteBags", "getWasteBags", "setWasteBags", "buildInput", "Lcom/climatechampion/model/CarbonInput;", "calculate", "", "goToStep", "step", "nextStep", "previousStep", "refreshEstimate", "resetCalculator", "app_debug"})
public final class CarbonCalculatorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _currentStep = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> currentStep = null;
    private float carKmPerWeek = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carType = "petrol";
    private int shortHaulFlights = 0;
    private int longHaulFlights = 0;
    private float publicTransitHours = 0.0F;
    private float electricityKwh = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String energySource = "grid";
    private float gasM3 = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String heatingType = "gas";
    private int householdSize = 1;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String recyclingHabit = "sometimes";
    private boolean compostsFood = false;
    private int wasteBags = 1;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.climatechampion.model.CarbonResult> _result = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.climatechampion.model.CarbonResult> result = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _pointsAwarded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> pointsAwarded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Float> _liveEstimate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Float> liveEstimate = null;
    
    public CarbonCalculatorViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCurrentStep() {
        return null;
    }
    
    public final float getCarKmPerWeek() {
        return 0.0F;
    }
    
    public final void setCarKmPerWeek(float p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarType() {
        return null;
    }
    
    public final void setCarType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getShortHaulFlights() {
        return 0;
    }
    
    public final void setShortHaulFlights(int p0) {
    }
    
    public final int getLongHaulFlights() {
        return 0;
    }
    
    public final void setLongHaulFlights(int p0) {
    }
    
    public final float getPublicTransitHours() {
        return 0.0F;
    }
    
    public final void setPublicTransitHours(float p0) {
    }
    
    public final float getElectricityKwh() {
        return 0.0F;
    }
    
    public final void setElectricityKwh(float p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEnergySource() {
        return null;
    }
    
    public final void setEnergySource(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final float getGasM3() {
        return 0.0F;
    }
    
    public final void setGasM3(float p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHeatingType() {
        return null;
    }
    
    public final void setHeatingType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getHouseholdSize() {
        return 0;
    }
    
    public final void setHouseholdSize(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecyclingHabit() {
        return null;
    }
    
    public final void setRecyclingHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean getCompostsFood() {
        return false;
    }
    
    public final void setCompostsFood(boolean p0) {
    }
    
    public final int getWasteBags() {
        return 0;
    }
    
    public final void setWasteBags(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.climatechampion.model.CarbonResult> getResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getPointsAwarded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Float> getLiveEstimate() {
        return null;
    }
    
    /**
     * Recomputes the running annual total from the current form values.
     */
    public final void refreshEstimate() {
    }
    
    public final void nextStep() {
    }
    
    public final void previousStep() {
    }
    
    public final void goToStep(int step) {
    }
    
    /**
     * Builds the engine input from the current form values (also usable for a live estimate).
     */
    @org.jetbrains.annotations.NotNull()
    public final com.climatechampion.model.CarbonInput buildInput() {
        return null;
    }
    
    public final void calculate() {
    }
    
    public final void resetCalculator() {
    }
}