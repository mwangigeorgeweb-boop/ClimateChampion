package com.climatechampion.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.climatechampion.databinding.FragmentStepWasteBinding
import com.climatechampion.viewmodel.CarbonCalculatorViewModel

/**
 * Step 3 — Waste & Recycling inputs. Final step triggers calculation.
 * Every change updates the ViewModel and the live estimate straight away.
 */
class WasteStepFragment : Fragment() {

    private var _binding: FragmentStepWasteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CarbonCalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepWasteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the range FIRST: setting progress before max clamps restored values
        binding.seekbarWasteBags.max = 9   // 1–10 bags per week

        // Restore values
        binding.seekbarWasteBags.progress = (viewModel.wasteBags - 1).coerceIn(0, 9)
        binding.tvWasteBagsValue.text     = "${viewModel.wasteBags} bags/week"
        binding.switchCompost.isChecked   = viewModel.compostsFood

        when (viewModel.recyclingHabit) {
            "always"    -> binding.chipRecycleAlways.isChecked    = true
            "sometimes" -> binding.chipRecycleSometimes.isChecked = true
            "rarely"    -> binding.chipRecycleRarely.isChecked    = true
            "never"     -> binding.chipRecycleNever.isChecked     = true
        }

        // Waste bags seekbar (1–10)
        binding.seekbarWasteBags.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                val bags = progress + 1
                binding.tvWasteBagsValue.text = "$bags bags/week"
                viewModel.wasteBags = bags
                viewModel.refreshEstimate()
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        })

        // Recycling habit chips
        binding.chipGroupRecycling.setOnCheckedStateChangeListener { _, _ ->
            viewModel.recyclingHabit = when {
                binding.chipRecycleAlways.isChecked    -> "always"
                binding.chipRecycleRarely.isChecked    -> "rarely"
                binding.chipRecycleNever.isChecked     -> "never"
                else                                   -> "sometimes"
            }
            viewModel.refreshEstimate()
        }

        // Compost switch
        binding.switchCompost.setOnCheckedChangeListener { _, isChecked ->
            viewModel.compostsFood = isChecked
            viewModel.refreshEstimate()
        }

        binding.btnBack.setOnClickListener { viewModel.previousStep() }

        // Calculate button — final step
        binding.btnCalculate.setOnClickListener {
            binding.btnCalculate.isEnabled = false
            binding.btnCalculate.text      = "Calculating... 🌍"
            viewModel.calculate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
