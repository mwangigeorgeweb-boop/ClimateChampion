package com.climatechampion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.climatechampion.model.CarbonCalculatorEngine
import com.climatechampion.model.CarbonInput
import com.climatechampion.model.CarbonResult

/**
 * ViewModel for the Carbon Footprint Calculator.
 * Holds form state across the 3 step pages and exposes the final result.
 */
class CarbonCalculatorViewModel : ViewModel() {

    // ── Step tracking (0 = Transport, 1 = Home Energy, 2 = Waste) ────────────
    private val _currentStep = MutableLiveData(0)
    val currentStep: LiveData<Int> = _currentStep

    // ── Form inputs (mutated as user fills each page) ─────────────────────────
    var carKmPerWeek         = 0f
    var carType              = "petrol"
    var shortHaulFlights     = 0
    var longHaulFlights      = 0
    var publicTransitHours   = 0f

    var electricityKwh       = 0f
    var energySource         = "grid"
    var gasM3                = 0f
    var heatingType          = "gas"
    var householdSize        = 1

    var recyclingHabit       = "sometimes"
    var compostsFood         = false
    var wasteBags            = 1 // matches the slider default ("1 bag/week")

    // ── Result ────────────────────────────────────────────────────────────────
    private val _result = MutableLiveData<CarbonResult?>()
    val result: LiveData<CarbonResult?> = _result

    private val _pointsAwarded = MutableLiveData<Boolean>()
    val pointsAwarded: LiveData<Boolean> = _pointsAwarded

    // ── Live estimate (updates while the user moves sliders / taps options) ───
    private val _liveEstimate = MutableLiveData(0f)
    val liveEstimate: LiveData<Float> = _liveEstimate

    init { refreshEstimate() }

    /** Recomputes the running annual total from the current form values. */
    fun refreshEstimate() {
        _liveEstimate.value = CarbonCalculatorEngine.calculate(buildInput()).totalEmissions
    }

    // ── Navigation ────────────────────────────────────────────────────────────

    fun nextStep() {
        val current = _currentStep.value ?: 0
        refreshEstimate()
        if (current < 2) _currentStep.value = current + 1
        else calculate()
    }

    fun previousStep() {
        val current = _currentStep.value ?: 0
        if (current > 0) _currentStep.value = current - 1
    }

    fun goToStep(step: Int) {
        if (step in 0..2) _currentStep.value = step
    }

    // ── Calculation ───────────────────────────────────────────────────────────

    /** Builds the engine input from the current form values (also usable for a live estimate). */
    fun buildInput() = CarbonInput(
        carKmPerWeek              = carKmPerWeek,
        carType                   = carType,
        shortHaulFlightsPerYear   = shortHaulFlights,
        longHaulFlightsPerYear    = longHaulFlights,
        publicTransitHoursPerWeek = publicTransitHours,
        electricityKwhPerMonth    = electricityKwh,
        energySource              = energySource,
        gasM3PerMonth             = gasM3,
        heatingType               = heatingType,
        householdSize             = householdSize,
        recyclingHabit            = recyclingHabit,
        compostsFood              = compostsFood,
        wastebagsPerWeek          = wasteBags
    )

    fun calculate() {
        _result.value = CarbonCalculatorEngine.calculate(buildInput())
        _pointsAwarded.value = true
    }

    fun resetCalculator() {
        carKmPerWeek       = 0f; carType = "petrol"
        shortHaulFlights   = 0;  longHaulFlights = 0; publicTransitHours = 0f
        electricityKwh     = 0f; energySource = "grid"; gasM3 = 0f
        heatingType        = "gas"; householdSize = 1
        recyclingHabit     = "sometimes"; compostsFood = false; wasteBags = 1
        _currentStep.value = 0
        _result.value      = null
        _pointsAwarded.value = false
        refreshEstimate()
    }
}
