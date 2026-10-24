package com.climatechampion.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.climatechampion.R
import com.climatechampion.databinding.FragmentQuizResultBinding

/**
 * Displays the quiz result: score, percentage, pass/fail state, and points earned.
 * Provides Retry, Home, and Topics navigation actions.
 */
class QuizResultFragment : Fragment() {

    private var _binding: FragmentQuizResultBinding? = null
    private val binding get() = _binding!!
    private val args: QuizResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindResults()
        setupClickListeners()
    }

    private fun bindResults() {
        val score = args.score
        val total = args.totalQuestions
        val percentage = args.percentage
        val passed = args.passed
        val points = args.pointsEarned

        binding.tvScore.text = getString(R.string.quiz_score_format, score, total)
        binding.tvPercentage.text = getString(R.string.percent_format, percentage.toInt())
        binding.progressScore.progress = percentage.toInt()

        if (passed) {
            binding.tvPassFail.text = getString(R.string.passed)
            binding.tvPassFail.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.correct_green)
            )
            binding.ivResultIcon.setImageResource(R.drawable.ic_trophy)
        } else {
            binding.tvPassFail.text = getString(R.string.failed)
            binding.tvPassFail.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.incorrect_red)
            )
            binding.ivResultIcon.setImageResource(R.drawable.ic_retry)
        }

        binding.tvPointsEarned.text = if (points > 0) {
            getString(R.string.points_earned_format, points)
        } else {
            getString(R.string.no_points_earned)
        }

        // Motivational message
        binding.tvMessage.text = when {
            percentage >= 100f -> getString(R.string.perfect_score_message)
            passed -> getString(R.string.passed_message)
            else -> getString(R.string.failed_message)
        }
    }

    private fun setupClickListeners() {
        binding.btnRetry.setOnClickListener {
            val action = QuizResultFragmentDirections
                .actionQuizResultFragmentToQuizFragment(args.topicId)
            // Replace this result screen so Back from the new attempt doesn't stack results
            findNavController().navigate(
                action,
                navOptions { popUpTo(R.id.quizResultFragment) { inclusive = true } }
            )
        }

        binding.btnHome.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, false)
        }

        binding.btnTopics.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
