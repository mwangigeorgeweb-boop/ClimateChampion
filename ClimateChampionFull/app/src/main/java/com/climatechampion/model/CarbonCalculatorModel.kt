package com.climatechampion.model

/**
 * Holds all user inputs for the carbon footprint calculator.
 * Values are stored in their raw units; CarbonCalculator converts them to kg CO2/year.
 */
data class CarbonInput(

    // ── Transport ─────────────────────────────────────────────────────────────
    /** How many km driven by personal petrol/diesel car per week */
    val carKmPerWeek: Float = 0f,

    /** Car type: "petrol", "diesel", "hybrid", "electric" */
    val carType: String = "petrol",

    /** Number of short-haul flights (< 3 hrs) per year */
    val shortHaulFlightsPerYear: Int = 0,

    /** Number of long-haul flights (> 3 hrs) per year */
    val longHaulFlightsPerYear: Int = 0,

    /** Public transit hours per week (bus, train, metro) */
    val publicTransitHoursPerWeek: Float = 0f,

    // ── Home Energy ───────────────────────────────────────────────────────────
    /** Monthly electricity bill in kWh */
    val electricityKwhPerMonth: Float = 0f,

    /** Energy source: "grid", "renewable", "mixed" */
    val energySource: String = "grid",

    /** Natural gas usage in cubic metres per month (0 if none) */
    val gasM3PerMonth: Float = 0f,

    /** Heating type: "gas", "electric", "oil", "heat_pump", "none" */
    val heatingType: String = "gas",

    /** Number of people sharing the household (to divide home emissions) */
    val householdSize: Int = 1,

    // ── Waste & Recycling ─────────────────────────────────────────────────────
    /** Recycling habit: "always", "sometimes", "rarely", "never" */
    val recyclingHabit: String = "sometimes",

    /** Composting food waste: true/false */
    val compostsFood: Boolean = false,

    /** Bags of general waste per week */
    val wastebagsPerWeek: Int = 2
)

/**
 * The result of a carbon footprint calculation.
 * All emissions are in kg CO2e per year.
 */
data class CarbonResult(
    val transportEmissions: Float,
    val homeEnergyEmissions: Float,
    val wasteEmissions: Float,
    val totalEmissions: Float,
    val comparisonToAverage: Float,   // percentage vs world average (4,700 kg/yr)
    val grade: String,                 // "A", "B", "C", "D", "F"
    val gradeColor: Int,               // color resource id
    val tips: List<CarbonTip>,
    val pointsEarned: Int = 50
)

/**
 * A single personalised tip to help reduce the user's footprint.
 */
data class CarbonTip(
    val category: String,    // "Transport", "Home Energy", "Waste"
    val icon: String,        // emoji
    val title: String,
    val description: String,
    val potentialSaving: String  // e.g. "Save ~400 kg CO2/yr"
)
