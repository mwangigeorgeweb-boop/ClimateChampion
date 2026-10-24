package com.climatechampion.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.climatechampion.databinding.FragmentStepTransportBinding
import com.climatechampion.viewmodel.CarbonCalculatorViewModel

/**
 * Step 1 — Transport inputs: car type, weekly km, flights, public transit.
 * Every change is written to the ViewModel straight away and refreshes the live estimate.
 */
class TransportStepFragment : Fragment() {

    private var _binding: FragmentStepTransportBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CarbonCalculatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepTransportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore previous values if user navigated back
        binding.seekbarCarKm.progress     = viewModel.carKmPerWeek.toInt()
        binding.seekbarTransit.progress   = viewModel.publicTransitHours.toInt()
        binding.etShortFlights.setText(viewModel.shortHaulFlights.toString())
        binding.etLongFlights.setText(viewModel.longHaulFlights.toString())

        // Select car type chip
        when (viewModel.carType) {
            "petrol"   -> binding.chipPetrol.isChecked   = true
            "diesel"   -> binding.chipDiesel.isChecked   = true
            "hybrid"   -> binding.chipHybrid.isChecked   = true
            "electric" -> binding.chipElectric.isChecked = true
        }

        // Car km seekbar
        binding.tvCarKmValue.text = "${binding.seekbarCarKm.progress} km/week"
        binding.seekbarCarKm.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvCarKmValue.text = "$progress km/week"
                viewModel.carKmPerWeek = progress.toFloat()
                viewModel.refreshEstimate()
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        })

        // Transit hours seekbar
        binding.tvTransitValue.text = "${binding.seekbarTransit.progress} hrs/week"
        binding.seekbarTransit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvTransitValue.text = "$progress hrs/week"
                viewModel.publicTransitHours = progress.toFloat()
                viewModel.refreshEstimate()
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        })

        // Car type chips
        binding.chipGroupCarType.setOnCheckedStateChangeListener { _, _ ->
            viewModel.carType = when {
                binding.chipDiesel.isChecked   -> "diesel"
                binding.chipHybrid.isChecked   -> "hybrid"
                binding.chipElectric.isChecked -> "electric"
                else                           -> "petrol"
            }
            viewModel.refreshEstimate()
        }

        // Flights: update live while typing
        binding.etShortFlights.doAfterTextChanged {
            viewModel.shortHaulFlights = it?.toString()?.toIntOrNull() ?: 0
            viewModel.refreshEstimate()
        }
        binding.etLongFlights.doAfterTextChanged {
            viewModel.longHaulFlights = it?.toString()?.toIntOrNull() ?: 0
            viewModel.refreshEstimate()
        }

        // Next button
        binding.btnNext.setOnClickListener {
            viewModel.shortHaulFlights = binding.etShortFlights.text
                .toString().toIntOrNull() ?: 0
            viewModel.longHaulFlights  = binding.etLongFlights.text
                .toString().toIntOrNull() ?: 0
            viewModel.nextStep()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
