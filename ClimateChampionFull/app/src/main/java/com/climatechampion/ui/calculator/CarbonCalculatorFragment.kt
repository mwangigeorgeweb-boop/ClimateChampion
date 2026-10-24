package com.climatechampion.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.climatechampion.R
import com.climatechampion.databinding.FragmentCarbonCalculatorBinding
import com.climatechampion.model.CarbonCalculatorEngine
import com.climatechampion.viewmodel.CarbonCalculatorViewModel

/**
 * Host fragment for the 3-step Carbon Footprint Calculator.
 * Manages step indicator and swaps child fragments for each step.
 */
class CarbonCalculatorFragment : Fragment() {

    private var _binding: FragmentCarbonCalculatorBinding? = null
    private val binding get() = _binding!!
    val viewModel: CarbonCalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarbonCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        // Load first step
        loadStep(0)
    }

    private fun setupObservers() {
        viewModel.currentStep.observe(viewLifecycleOwner) { step ->
            loadStep(step)
            updateStepIndicator(step)
        }

        viewModel.liveEstimate.observe(viewLifecycleOwner) { total ->
            binding.tvLiveEstimate.text =
                "🌍 Live estimate: ${CarbonCalculatorEngine.formatEmissions(total)} per year"
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            result ?: return@observe
            val nav = findNavController()
            if (nav.currentDestination?.id == R.id.carbonCalculatorFragment) {
                nav.navigate(
                    CarbonCalculatorFragmentDirections
                        .actionCarbonCalculatorFragmentToCarbonResultFragment()
                )
            }
        }
    }

    private fun loadStep(step: Int) {
        val fragment: Fragment = when (step) {
            0    -> TransportStepFragment()
            1    -> HomeEnergyStepFragment()
            2    -> WasteStepFragment()
            else -> TransportStepFragment()
        }
        childFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.stepContainer, fragment)
            .commit()
    }

    private fun updateStepIndicator(step: Int) {
        // Progress bar: 33% / 66% / 100%
        binding.stepProgressBar.progress = ((step + 1) * 100) / 3

        // The header is green, so reached steps are white and upcoming steps are faded
        // (they used to be green-on-green and therefore invisible).
        val active = requireContext().getColor(R.color.white)
        val inactive = android.graphics.Color.parseColor("#99FFFFFF")
        binding.tvStep1.setTextColor(if (step >= 0) active else inactive)
        binding.tvStep2.setTextColor(if (step >= 1) active else inactive)
        binding.tvStep3.setTextColor(if (step >= 2) active else inactive)

        binding.tvStepTitle.text = when (step) {
            0    -> "Step 1 of 3 — Transport 🚗"
            1    -> "Step 2 of 3 — Home Energy 🏠"
            2    -> "Step 3 of 3 — Waste & Recycling ♻️"
            else -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
