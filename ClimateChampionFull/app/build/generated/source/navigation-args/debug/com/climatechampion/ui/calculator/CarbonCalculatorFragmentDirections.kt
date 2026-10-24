package com.climatechampion.ui.calculator

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.climatechampion.R

public class CarbonCalculatorFragmentDirections private constructor() {
  public companion object {
    public fun actionCarbonCalculatorFragmentToCarbonResultFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_carbonCalculatorFragment_to_carbonResultFragment)
  }
}
