package com.climatechampion.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.climatechampion.R
import com.climatechampion.databinding.FragmentCarbonResultBinding
import com.climatechampion.model.CarbonCalculatorEngine
import com.climatechampion.utils.toast
import com.climatechampion.viewmodel.CarbonCalculatorViewModel

/**
 * Shows the final carbon footprint result:
 * total emissions, grade, breakdown chart, personalised tips, and points earned.
 */
class CarbonResultFragment : Fragment() {

    private var _binding: FragmentCarbonResultBinding? = null
    private val binding get() = _binding!!

    // Share ViewModel with activity so we read the result it computed
    private val viewModel: CarbonCalculatorViewModel by activityViewModels()

    private lateinit var tipsAdapter: CarbonTipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarbonResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTipsRecycler()

        viewModel.result.observe(viewLifecycleOwner) { result ->
            result ?: return@observe
            bindResult(result)
        }

        viewModel.pointsAwarded.observe(viewLifecycleOwner) { awarded ->
            if (awarded) toast(getString(R.string.points_awarded, 50))
        }

        binding.btnRecalculate.setOnClickListener {
            viewModel.resetCalculator()
            findNavController().navigateUp()
        }

        binding.btnShareResult.setOnClickListener {
            shareResult()
        }
    }

    private fun setupTipsRecycler() {
        tipsAdapter = CarbonTipsAdapter()
        binding.rvTips.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tipsAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun bindResult(result: com.climatechampion.model.CarbonResult) {
        // Total
        binding.tvTotalEmissions.text = CarbonCalculatorEngine.formatEmissions(result.totalEmissions)
        binding.tvTotalLabel.text     = "Your Annual Carbon Footprint"

        // Grade
        binding.tvGrade.text = result.grade
        binding.tvGrade.setTextColor(
            ContextCompat.getColor(requireContext(), result.gradeColor)
        )

        // Comparison to world average
        val compText = when {
            result.comparisonToAverage < 80f  ->
                "🌟 ${(100f - result.comparisonToAverage).toInt()}% BELOW world average"
            result.comparisonToAverage < 120f ->
                "📊 About the world average (4.7 tonnes/yr)"
            else ->
                "⚠️ ${(result.comparisonToAverage - 100f).toInt()}% ABOVE world average"
        }
        binding.tvComparison.text = compText

        // Breakdown bars
        val total = result.totalEmissions.coerceAtLeast(1f)
        binding.tvTransportEmissions.text =
            CarbonCalculatorEngine.formatEmissions(result.transportEmissions)
        binding.progressTransport.progress =
            ((result.transportEmissions / total) * 100f).toInt()

        binding.tvHomeEmissions.text =
            CarbonCalculatorEngine.formatEmissions(result.homeEnergyEmissions)
        binding.progressHome.progress =
            ((result.homeEnergyEmissions / total) * 100f).toInt()

        binding.tvWasteEmissions.text =
            CarbonCalculatorEngine.formatEmissions(result.wasteEmissions)
        binding.progressWaste.progress =
            ((result.wasteEmissions / total) * 100f).toInt()

        // Points
        binding.tvPointsEarned.text =
            getString(R.string.points_earned_format, result.pointsEarned)

        // Tips
        tipsAdapter.submitList(result.tips)
    }

    private fun shareResult() {
        val result = viewModel.result.value ?: return
        val text = """
            🌍 My Carbon Footprint — Climate Champion
            
            Annual footprint: ${CarbonCalculatorEngine.formatEmissions(result.totalEmissions)}
            Grade: ${result.grade}
            
            🚗 Transport: ${CarbonCalculatorEngine.formatEmissions(result.transportEmissions)}
            🏠 Home Energy: ${CarbonCalculatorEngine.formatEmissions(result.homeEnergyEmissions)}
            ♻️ Waste: ${CarbonCalculatorEngine.formatEmissions(result.wasteEmissions)}
            
            Calculate yours on Climate Champion!
        """.trimIndent()

        val intent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(android.content.Intent.EXTRA_TEXT, text)
        }
        startActivity(android.content.Intent.createChooser(intent, "Share your footprint"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
