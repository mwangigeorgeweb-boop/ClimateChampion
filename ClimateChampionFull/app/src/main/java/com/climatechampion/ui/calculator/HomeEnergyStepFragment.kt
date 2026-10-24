package com.climatechampion.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.climatechampion.databinding.FragmentStepHomeEnergyBinding
import com.climatechampion.viewmodel.CarbonCalculatorViewModel

/**
 * Step 2 — Home Energy inputs: electricity, gas, heating type, household size.
 * Every change updates the ViewModel and the live estimate straight away.
 */
class HomeEnergyStepFragment : Fragment() {

    private var _binding: FragmentStepHomeEnergyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CarbonCalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepHomeEnergyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the ranges FIRST: setting progress before max clamps restored values
        binding.seekbarElectricity.max = 600   // kWh per month
        binding.seekbarGas.max         = 200   // m³ per month
        binding.seekbarHousehold.max   = 5     // 1–6 people

        // Restore values
        binding.seekbarElectricity.progress = viewModel.electricityKwh.toInt()
        binding.seekbarGas.progress         = viewModel.gasM3.toInt()
        binding.seekbarHousehold.progress   = (viewModel.householdSize - 1).coerceIn(0, 5)
        binding.tvElectricityValue.text     = "${viewModel.electricityKwh.toInt()} kWh/month"
        binding.tvGasValue.text             = "${viewModel.gasM3.toInt()} m³/month"
        binding.tvHouseholdValue.text       = "${viewModel.householdSize} people"

        when (viewModel.energySource) {
            "renewable" -> binding.chipRenewable.isChecked = true
            "mixed"     -> binding.chipMixed.isChecked     = true
            else        -> binding.chipGrid.isChecked       = true
        }
        when (viewModel.heatingType) {
            "electric"  -> binding.chipHeatingElectric.isChecked  = true
            "oil"       -> binding.chipHeatingOil.isChecked        = true
            "heat_pump" -> binding.chipHeatingHeatPump.isChecked   = true
            "none"      -> binding.chipHeatingNone.isChecked       = true
            else        -> binding.chipHeatingGas.isChecked        = true
        }

        // Electricity seekbar (0–600 kWh)
        binding.seekbarElectricity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvElectricityValue.text = "$progress kWh/month"
                viewModel.electricityKwh = progress.toFloat()
                viewModel.refreshEstimate()
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        })

        // Gas seekbar (0–200 m³)
        binding.seekbarGas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvGasValue.text = "$progress m³/month"
                viewModel.gasM3 = progress.toFloat()
                viewModel.refreshEstimate()
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        })

        // Household size seekbar (1–6)
        binding.seekbarHousehold.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                val size = progress + 1
                binding.tvHouseholdValue.text = "$size people"
                viewModel.householdSize = size
                viewModel.refreshEstimate()
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        })

        // Energy source chips
        binding.chipGroupEnergy.setOnCheckedStateChangeListener { _, _ ->
            viewModel.energySource = when {
                binding.chipRenewable.isChecked -> "renewable"
                binding.chipMixed.isChecked     -> "mixed"
                else                            -> "grid"
            }
            viewModel.refreshEstimate()
        }

        // Heating type chips
        binding.chipGroupHeating.setOnCheckedStateChangeListener { _, _ ->
            viewModel.heatingType = when {
                binding.chipHeatingElectric.isChecked  -> "electric"
                binding.chipHeatingOil.isChecked        -> "oil"
                binding.chipHeatingHeatPump.isChecked   -> "heat_pump"
                binding.chipHeatingNone.isChecked       -> "none"
                else                                    -> "gas"
            }
            viewModel.refreshEstimate()
        }

        binding.btnBack.setOnClickListener { viewModel.previousStep() }
        binding.btnNext.setOnClickListener { viewModel.nextStep() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
