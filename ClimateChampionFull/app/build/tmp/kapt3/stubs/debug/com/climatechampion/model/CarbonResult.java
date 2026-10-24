package com.climatechampion.model;

/**
 * The result of a carbon footprint calculation.
 * All emissions are in kg CO2e per year.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\tH\u00c6\u0003J\t\u0010$\u001a\u00020\u000bH\u00c6\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\t\u0010&\u001a\u00020\u000bH\u00c6\u0003Ji\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\u000bH\u00d6\u0001J\t\u0010,\u001a\u00020\tH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u000f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012\u00a8\u0006-"}, d2 = {"Lcom/climatechampion/model/CarbonResult;", "", "transportEmissions", "", "homeEnergyEmissions", "wasteEmissions", "totalEmissions", "comparisonToAverage", "grade", "", "gradeColor", "", "tips", "", "Lcom/climatechampion/model/CarbonTip;", "pointsEarned", "(FFFFFLjava/lang/String;ILjava/util/List;I)V", "getComparisonToAverage", "()F", "getGrade", "()Ljava/lang/String;", "getGradeColor", "()I", "getHomeEnergyEmissions", "getPointsEarned", "getTips", "()Ljava/util/List;", "getTotalEmissions", "getTransportEmissions", "getWasteEmissions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class CarbonResult {
    private final float transportEmissions = 0.0F;
    private final float homeEnergyEmissions = 0.0F;
    private final float wasteEmissions = 0.0F;
    private final float totalEmissions = 0.0F;
    private final float comparisonToAverage = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String grade = null;
    private final int gradeColor = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.climatechampion.model.CarbonTip> tips = null;
    private final int pointsEarned = 0;
    
    public CarbonResult(float transportEmissions, float homeEnergyEmissions, float wasteEmissions, float totalEmissions, float comparisonToAverage, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, int gradeColor, @org.jetbrains.annotations.NotNull()
    java.util.List<com.climatechampion.model.CarbonTip> tips, int pointsEarned) {
        super();
    }
    
    public final float getTransportEmissions() {
        return 0.0F;
    }
    
    public final float getHomeEnergyEmissions() {
        return 0.0F;
    }
    
    public final float getWasteEmissions() {
        return 0.0F;
    }
    
    public final float getTotalEmissions() {
        return 0.0F;
    }
    
    public final float getComparisonToAverage() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGrade() {
        return null;
    }
    
    public final int getGradeColor() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.climatechampion.model.CarbonTip> getTips() {
        return null;
    }
    
    public final int getPointsEarned() {
        return 0;
    }
    
    public final float component1() {
        return 0.0F;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.climatechampion.model.CarbonTip> component8() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.climatechampion.model.CarbonResult copy(float transportEmissions, float homeEnergyEmissions, float wasteEmissions, float totalEmissions, float comparisonToAverage, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, int gradeColor, @org.jetbrains.annotations.NotNull()
    java.util.List<com.climatechampion.model.CarbonTip> tips, int pointsEarned) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}