package com.climatechampion.model

import android.graphics.Color

/**
 * Pure calculation engine for carbon footprint.
 * All emission factors are based on internationally recognised sources
 * (IPCC, UK DEFRA, EPA) in kg CO2e per unit.
 */
object CarbonCalculatorEngine {

    // ── Emission Factors ──────────────────────────────────────────────────────

    // Transport (kg CO2e per km)
    private const val EF_CAR_PETROL   = 0.192f
    private const val EF_CAR_DIESEL   = 0.171f
    private const val EF_CAR_HYBRID   = 0.110f
    private const val EF_CAR_ELECTRIC = 0.053f

    // Flights (kg CO2e per flight, average seat, including radiative forcing)
    private const val EF_FLIGHT_SHORT = 255f   // e.g. London–Paris
    private const val EF_FLIGHT_LONG  = 1620f  // e.g. London–New York

    // Public transit (kg CO2e per hour of travel, mixed modes)
    private const val EF_PUBLIC_TRANSIT_PER_HOUR = 0.089f

    // Home energy (kg CO2e per kWh)
    private const val EF_ELECTRICITY_GRID      = 0.233f
    private const val EF_ELECTRICITY_MIXED     = 0.140f
    private const val EF_ELECTRICITY_RENEWABLE = 0.020f

    // Gas (kg CO2e per m³)
    private const val EF_GAS_PER_M3 = 2.04f

    // Heating supplements (kg CO2e per year, additional)
    private const val EF_HEATING_OIL       = 820f
    private const val EF_HEATING_HEATPUMP  = 200f

    // Waste (kg CO2e per bag per week per year)
    private const val EF_WASTE_BAG_PER_YEAR = 29f  // per bag/week annualised

    // Recycling reduction factors
    private val RECYCLING_FACTOR = mapOf(
        "always"    to 0.55f,
        "sometimes" to 0.75f,
        "rarely"    to 0.90f,
        "never"     to 1.00f
    )

    // World average annual footprint (kg CO2e) for comparison
    private const val WORLD_AVERAGE = 4700f

    // ── Main Calculate Function ───────────────────────────────────────────────

    fun calculate(input: CarbonInput): CarbonResult {

        // 1. Transport
        val carEf = when (input.carType) {
            "diesel"   -> EF_CAR_DIESEL
            "hybrid"   -> EF_CAR_HYBRID
            "electric" -> EF_CAR_ELECTRIC
            else       -> EF_CAR_PETROL
        }
        val carAnnual       = input.carKmPerWeek * 52f * carEf
        val shortFlights    = input.shortHaulFlightsPerYear * EF_FLIGHT_SHORT
        val longFlights     = input.longHaulFlightsPerYear  * EF_FLIGHT_LONG
        val transitAnnual   = input.publicTransitHoursPerWeek * 52f * EF_PUBLIC_TRANSIT_PER_HOUR
        val transportTotal  = carAnnual + shortFlights + longFlights + transitAnnual

        // 2. Home Energy
        val elecEf = when (input.energySource) {
            "renewable" -> EF_ELECTRICITY_RENEWABLE
            "mixed"     -> EF_ELECTRICITY_MIXED
            else        -> EF_ELECTRICITY_GRID
        }
        val elecAnnual    = input.electricityKwhPerMonth * 12f * elecEf
        val gasAnnual     = input.gasM3PerMonth * 12f * EF_GAS_PER_M3
        val heatingExtra  = when (input.heatingType) {
            "oil"       -> EF_HEATING_OIL
            "heat_pump" -> EF_HEATING_HEATPUMP
            else        -> 0f
        }
        val homeRaw       = elecAnnual + gasAnnual + heatingExtra
        val homePerPerson = homeRaw / input.householdSize.coerceAtLeast(1)

        // 3. Waste & Recycling
        val recyclingFactor = RECYCLING_FACTOR[input.recyclingHabit] ?: 1.0f
        val compostFactor   = if (input.compostsFood) 0.85f else 1.0f
        val wasteTotal      = input.wastebagsPerWeek * EF_WASTE_BAG_PER_YEAR *
                              recyclingFactor * compostFactor

        // 4. Total
        val total = transportTotal + homePerPerson + wasteTotal

        // 5. Grade
        val grade = when {
            total < 1500f -> "A"
            total < 2500f -> "B"
            total < 4000f -> "C"
            total < 6000f -> "D"
            else          -> "F"
        }
        val gradeColor = when (grade) {
            "A"  -> android.R.color.holo_green_dark
            "B"  -> android.R.color.holo_green_light
            "C"  -> android.R.color.holo_orange_light
            "D"  -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_red_dark
        }

        // 6. Comparison to world average
        val comparison = (total / WORLD_AVERAGE) * 100f

        // 7. Personalised tips
        val tips = buildTips(input, carAnnual, homePerPerson, wasteTotal)

        return CarbonResult(
            transportEmissions  = transportTotal,
            homeEnergyEmissions = homePerPerson,
            wasteEmissions      = wasteTotal,
            totalEmissions      = total,
            comparisonToAverage = comparison,
            grade               = grade,
            gradeColor          = gradeColor,
            tips                = tips,
            pointsEarned        = 50
        )
    }

    // ── Tip Generator ─────────────────────────────────────────────────────────

    private fun buildTips(
        input: CarbonInput,
        carEmissions: Float,
        homeEmissions: Float,
        wasteEmissions: Float
    ): List<CarbonTip> {
        val tips = mutableListOf<CarbonTip>()

        // Transport tips
        if (input.carType == "petrol" || input.carType == "diesel") {
            tips.add(CarbonTip(
                category = "Transport",
                icon = "⚡",
                title = "Switch to an Electric Vehicle",
                description = "EVs emit up to 70% less CO2 than petrol cars over their lifetime, even accounting for battery production.",
                potentialSaving = "Save ~${(carEmissions * 0.70f).toInt()} kg CO2/yr"
            ))
        }
        if (input.carKmPerWeek > 100) {
            tips.add(CarbonTip(
                category = "Transport",
                icon = "🚲",
                title = "Replace Short Trips with Cycling",
                description = "Swapping just 3 car trips per week for cycling or walking can meaningfully cut your transport footprint.",
                potentialSaving = "Save ~${(input.carKmPerWeek * 0.20f * 52f * EF_CAR_PETROL).toInt()} kg CO2/yr"
            ))
        }
        if (input.longHaulFlightsPerYear > 1) {
            tips.add(CarbonTip(
                category = "Transport",
                icon = "✈️",
                title = "Reduce Long-Haul Flights",
                description = "One long-haul flight can produce more CO2 than months of driving. Consider train travel or video calls for business.",
                potentialSaving = "Save ~${(EF_FLIGHT_LONG).toInt()} kg CO2 per avoided flight"
            ))
        }
        if (input.publicTransitHoursPerWeek < 2f && input.carKmPerWeek > 50f) {
            tips.add(CarbonTip(
                category = "Transport",
                icon = "🚌",
                title = "Use Public Transport More",
                description = "Buses and trains emit 4–10x less CO2 per passenger than single-occupancy cars.",
                potentialSaving = "Save up to 1,200 kg CO2/yr"
            ))
        }

        // Home energy tips
        if (input.energySource == "grid") {
            tips.add(CarbonTip(
                category = "Home Energy",
                icon = "☀️",
                title = "Switch to a Green Energy Tariff",
                description = "Switching your electricity to a 100% renewable tariff is one of the fastest ways to cut home emissions.",
                potentialSaving = "Save ~${(input.electricityKwhPerMonth * 12f * (EF_ELECTRICITY_GRID - EF_ELECTRICITY_RENEWABLE)).toInt()} kg CO2/yr"
            ))
        }
        if (input.electricityKwhPerMonth > 300f) {
            tips.add(CarbonTip(
                category = "Home Energy",
                icon = "💡",
                title = "Reduce Electricity Consumption",
                description = "Switch to LED bulbs, use smart power strips, and wash clothes at 30°C to cut electricity use by up to 25%.",
                potentialSaving = "Save ~${(input.electricityKwhPerMonth * 0.25f * 12f * EF_ELECTRICITY_GRID).toInt()} kg CO2/yr"
            ))
        }
        if (input.heatingType == "gas" || input.heatingType == "oil") {
            tips.add(CarbonTip(
                category = "Home Energy",
                icon = "🔥",
                title = "Install a Heat Pump",
                description = "Heat pumps are 3x more efficient than gas boilers and run on electricity, which can be renewable.",
                potentialSaving = "Save 600–1,200 kg CO2/yr"
            ))
        }
        if (input.gasM3PerMonth > 50f) {
            tips.add(CarbonTip(
                category = "Home Energy",
                icon = "🌡️",
                title = "Turn Down the Thermostat",
                description = "Reducing your heating by just 1°C can cut your heating bill and emissions by around 10%.",
                potentialSaving = "Save ~${(input.gasM3PerMonth * 12f * EF_GAS_PER_M3 * 0.10f).toInt()} kg CO2/yr"
            ))
        }

        // Waste tips
        if (input.recyclingHabit == "rarely" || input.recyclingHabit == "never") {
            tips.add(CarbonTip(
                category = "Waste",
                icon = "♻️",
                title = "Start Recycling Consistently",
                description = "Recycling paper, plastic, glass, and metal significantly reduces the energy needed to produce new materials.",
                potentialSaving = "Save ~${(wasteEmissions * 0.40f).toInt()} kg CO2/yr"
            ))
        }
        if (!input.compostsFood) {
            tips.add(CarbonTip(
                category = "Waste",
                icon = "🌱",
                title = "Start Composting Food Waste",
                description = "Food in landfill produces methane, a potent greenhouse gas. Composting diverts waste and enriches soil.",
                potentialSaving = "Save ~${(wasteEmissions * 0.15f).toInt()} kg CO2/yr"
            ))
        }
        if (input.wastebagsPerWeek > 2) {
            tips.add(CarbonTip(
                category = "Waste",
                icon = "🛍️",
                title = "Reduce Overall Waste",
                description = "Buy only what you need, choose products with less packaging, and repair items instead of replacing them.",
                potentialSaving = "Save ~${((input.wastebagsPerWeek - 1) * EF_WASTE_BAG_PER_YEAR).toInt()} kg CO2/yr"
            ))
        }

        // Return top 5 most impactful tips
        return tips.take(5)
    }

    /** Formats a kg CO2 value for display e.g. "2.4 tonnes" or "850 kg" */
    fun formatEmissions(kg: Float): String = when {
        kg >= 1000f -> String.format("%.1f tonnes", kg / 1000f)
        else        -> "${kg.toInt()} kg"
    }
}
