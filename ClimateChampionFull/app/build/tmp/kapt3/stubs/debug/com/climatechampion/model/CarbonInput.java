package com.climatechampion.model;

/**
 * Holds all user inputs for the carbon footprint calculator.
 * Values are stored in their raw units; CarbonCalculator converts them to kg CO2/year.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b&\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0011H\u00c6\u0003J\t\u0010)\u001a\u00020\u0007H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0007H\u00c6\u0003J\t\u0010,\u001a\u00020\u0007H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\u008b\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0007H\u00c6\u0001J\u0013\u00103\u001a\u00020\u00112\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00105\u001a\u00020\u0007H\u00d6\u0001J\t\u00106\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001f\u00a8\u00067"}, d2 = {"Lcom/climatechampion/model/CarbonInput;", "", "carKmPerWeek", "", "carType", "", "shortHaulFlightsPerYear", "", "longHaulFlightsPerYear", "publicTransitHoursPerWeek", "electricityKwhPerMonth", "energySource", "gasM3PerMonth", "heatingType", "householdSize", "recyclingHabit", "compostsFood", "", "wastebagsPerWeek", "(FLjava/lang/String;IIFFLjava/lang/String;FLjava/lang/String;ILjava/lang/String;ZI)V", "getCarKmPerWeek", "()F", "getCarType", "()Ljava/lang/String;", "getCompostsFood", "()Z", "getElectricityKwhPerMonth", "getEnergySource", "getGasM3PerMonth", "getHeatingType", "getHouseholdSize", "()I", "getLongHaulFlightsPerYear", "getPublicTransitHoursPerWeek", "getRecyclingHabit", "getShortHaulFlightsPerYear", "getWastebagsPerWeek", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class CarbonInput {
    
    /**
     * How many km driven by personal petrol/diesel car per week
     */
    private final float carKmPerWeek = 0.0F;
    
    /**
     * Car type: "petrol", "diesel", "hybrid", "electric"
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String carType = null;
    
    /**
     * Number of short-haul flights (< 3 hrs) per year
     */
    private final int shortHaulFlightsPerYear = 0;
    
    /**
     * Number of long-haul flights (> 3 hrs) per year
     */
    private final int longHaulFlightsPerYear = 0;
    
    /**
     * Public transit hours per week (bus, train, metro)
     */
    private final float publicTransitHoursPerWeek = 0.0F;
    
    /**
     * Monthly electricity bill in kWh
     */
    private final float electricityKwhPerMonth = 0.0F;
    
    /**
     * Energy source: "grid", "renewable", "mixed"
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String energySource = null;
    
    /**
     * Natural gas usage in cubic metres per month (0 if none)
     */
    private final float gasM3PerMonth = 0.0F;
    
    /**
     * Heating type: "gas", "electric", "oil", "heat_pump", "none"
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String heatingType = null;
    
    /**
     * Number of people sharing the household (to divide home emissions)
     */
    private final int householdSize = 0;
    
    /**
     * Recycling habit: "always", "sometimes", "rarely", "never"
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String recyclingHabit = null;
    
    /**
     * Composting food waste: true/false
     */
    private final boolean compostsFood = false;
    
    /**
     * Bags of general waste per week
     */
    private final int wastebagsPerWeek = 0;
    
    public CarbonInput(float carKmPerWeek, @org.jetbrains.annotations.NotNull()
    java.lang.String carType, int shortHaulFlightsPerYear, int longHaulFlightsPerYear, float publicTransitHoursPerWeek, float electricityKwhPerMonth, @org.jetbrains.annotations.NotNull()
    java.lang.String energySource, float gasM3PerMonth, @org.jetbrains.annotations.NotNull()
    java.lang.String heatingType, int householdSize, @org.jetbrains.annotations.NotNull()
    java.lang.String recyclingHabit, boolean compostsFood, int wastebagsPerWeek) {
        super();
    }
    
    /**
     * How many km driven by personal petrol/diesel car per week
     */
    public final float getCarKmPerWeek() {
        return 0.0F;
    }
    
    /**
     * Car type: "petrol", "diesel", "hybrid", "electric"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarType() {
        return null;
    }
    
    /**
     * Number of short-haul flights (< 3 hrs) per year
     */
    public final int getShortHaulFlightsPerYear() {
        return 0;
    }
    
    /**
     * Number of long-haul flights (> 3 hrs) per year
     */
    public final int getLongHaulFlightsPerYear() {
        return 0;
    }
    
    /**
     * Public transit hours per week (bus, train, metro)
     */
    public final float getPublicTransitHoursPerWeek() {
        return 0.0F;
    }
    
    /**
     * Monthly electricity bill in kWh
     */
    public final float getElectricityKwhPerMonth() {
        return 0.0F;
    }
    
    /**
     * Energy source: "grid", "renewable", "mixed"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEnergySource() {
        return null;
    }
    
    /**
     * Natural gas usage in cubic metres per month (0 if none)
     */
    public final float getGasM3PerMonth() {
        return 0.0F;
    }
    
    /**
     * Heating type: "gas", "electric", "oil", "heat_pump", "none"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHeatingType() {
        return null;
    }
    
    /**
     * Number of people sharing the household (to divide home emissions)
     */
    public final int getHouseholdSize() {
        return 0;
    }
    
    /**
     * Recycling habit: "always", "sometimes", "rarely", "never"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecyclingHabit() {
        return null;
    }
    
    /**
     * Composting food waste: true/false
     */
    public final boolean getCompostsFood() {
        return false;
    }
    
    /**
     * Bags of general waste per week
     */
    public final int getWastebagsPerWeek() {
        return 0;
    }
    
    public CarbonInput() {
        super();
    }
    
    public final float component1() {
        return 0.0F;
    }
    
    public final int component10() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    public final int component13() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.climatechampion.model.CarbonInput copy(float carKmPerWeek, @org.jetbrains.annotations.NotNull()
    java.lang.String carType, int shortHaulFlightsPerYear, int longHaulFlightsPerYear, float publicTransitHoursPerWeek, float electricityKwhPerMonth, @org.jetbrains.annotations.NotNull()
    java.lang.String energySource, float gasM3PerMonth, @org.jetbrains.annotations.NotNull()
    java.lang.String heatingType, int householdSize, @org.jetbrains.annotations.NotNull()
    java.lang.String recyclingHabit, boolean compostsFood, int wastebagsPerWeek) {
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