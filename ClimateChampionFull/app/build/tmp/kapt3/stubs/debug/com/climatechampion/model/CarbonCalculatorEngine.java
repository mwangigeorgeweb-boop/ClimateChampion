package com.climatechampion.model;

/**
 * Pure calculation engine for carbon footprint.
 * All emission factors are based on internationally recognised sources
 * (IPCC, UK DEFRA, EPA) in kg CO2e per unit.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0002J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00040\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/climatechampion/model/CarbonCalculatorEngine;", "", "()V", "EF_CAR_DIESEL", "", "EF_CAR_ELECTRIC", "EF_CAR_HYBRID", "EF_CAR_PETROL", "EF_ELECTRICITY_GRID", "EF_ELECTRICITY_MIXED", "EF_ELECTRICITY_RENEWABLE", "EF_FLIGHT_LONG", "EF_FLIGHT_SHORT", "EF_GAS_PER_M3", "EF_HEATING_HEATPUMP", "EF_HEATING_OIL", "EF_PUBLIC_TRANSIT_PER_HOUR", "EF_WASTE_BAG_PER_YEAR", "RECYCLING_FACTOR", "", "", "WORLD_AVERAGE", "buildTips", "", "Lcom/climatechampion/model/CarbonTip;", "input", "Lcom/climatechampion/model/CarbonInput;", "carEmissions", "homeEmissions", "wasteEmissions", "calculate", "Lcom/climatechampion/model/CarbonResult;", "formatEmissions", "kg", "app_debug"})
public final class CarbonCalculatorEngine {
    private static final float EF_CAR_PETROL = 0.192F;
    private static final float EF_CAR_DIESEL = 0.171F;
    private static final float EF_CAR_HYBRID = 0.11F;
    private static final float EF_CAR_ELECTRIC = 0.053F;
    private static final float EF_FLIGHT_SHORT = 255.0F;
    private static final float EF_FLIGHT_LONG = 1620.0F;
    private static final float EF_PUBLIC_TRANSIT_PER_HOUR = 0.089F;
    private static final float EF_ELECTRICITY_GRID = 0.233F;
    private static final float EF_ELECTRICITY_MIXED = 0.14F;
    private static final float EF_ELECTRICITY_RENEWABLE = 0.02F;
    private static final float EF_GAS_PER_M3 = 2.04F;
    private static final float EF_HEATING_OIL = 820.0F;
    private static final float EF_HEATING_HEATPUMP = 200.0F;
    private static final float EF_WASTE_BAG_PER_YEAR = 29.0F;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, java.lang.Float> RECYCLING_FACTOR = null;
    private static final float WORLD_AVERAGE = 4700.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.climatechampion.model.CarbonCalculatorEngine INSTANCE = null;
    
    private CarbonCalculatorEngine() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.climatechampion.model.CarbonResult calculate(@org.jetbrains.annotations.NotNull()
    com.climatechampion.model.CarbonInput input) {
        return null;
    }
    
    private final java.util.List<com.climatechampion.model.CarbonTip> buildTips(com.climatechampion.model.CarbonInput input, float carEmissions, float homeEmissions, float wasteEmissions) {
        return null;
    }
    
    /**
     * Formats a kg CO2 value for display e.g. "2.4 tonnes" or "850 kg"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatEmissions(float kg) {
        return null;
    }
}