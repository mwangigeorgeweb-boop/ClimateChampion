package com.climatechampion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.climatechampion.R
import com.climatechampion.databinding.FragmentHomeBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.PointsCalculator
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.utils.toFormattedPoints
import com.climatechampion.viewmodel.ProfileViewModel
import com.climatechampion.viewmodel.TopicViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * Home screen — welcome message, user stats, featured topic card, quick navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()
    private val topicViewModel: TopicViewModel by viewModels()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGreeting()
        setupObservers()
        setupClickListeners()

        profileViewModel.loadProfile()
        topicViewModel.loadAllTopics()
    }

    private fun setupGreeting() {
        val name = auth.currentUser?.displayName?.split(" ")?.firstOrNull() ?: "Champion"
        binding.tvGreeting.text = getString(R.string.greeting_format, name)

        val photoUrl = auth.currentUser?.photoUrl
        if (photoUrl != null) {
            Glide.with(this)
                .load(photoUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .into(binding.ivAvatar)
        } else {
            binding.ivAvatar.setImageResource(R.drawable.ic_profile_placeholder)
        }
    }

    private fun setupObservers() {
        profileViewModel.user.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val user = result.data
                binding.tvPoints.text = getString(
                    R.string.points_format, user.totalPoints.toFormattedPoints()
                )
                binding.tvLevel.text = user.level
                binding.tvLevelEmoji.text = PointsCalculator.levelEmoji(user.level)
                binding.progressLevel.progress =
                    PointsCalculator.progressToNextLevel(user.totalPoints)
            }
        }

        profileViewModel.progress.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                binding.tvTopicsCompleted.text = result.data.count { it.completed }.toString()
            }
        }

        profileViewModel.quizResults.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val quizzes = result.data
                binding.tvQuizzesTaken.text = quizzes.size.toString()
                val avg = if (quizzes.isNotEmpty()) {
                    quizzes.map { it.percentage }.average().toInt()
                } else 0
                binding.tvAvgScore.text = getString(R.string.percent_format, avg)
            }
        }

        topicViewModel.topics.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.cardFeatured.hide()
                is NetworkResult.Success -> {
                    val featured = result.data.firstOrNull()
                    if (featured != null) {
                        binding.cardFeatured.show()
                        binding.tvFeaturedTitle.text = featured.title
                        binding.tvFeaturedDesc.text = featured.description
                        binding.tvFeaturedCategory.text = featured.category
                        Glide.with(this)
                            .load(featured.thumbnailUrl)
                            .placeholder(R.drawable.ic_topic_placeholder)
                            .into(binding.ivFeaturedThumb)
                        binding.cardFeatured.setOnClickListener {
                            val action = HomeFragmentDirections
                                .actionHomeFragmentToTopicDetailFragment(featured.topicId)
                            findNavController().navigate(action)
                        }
                    } else {
                        binding.cardFeatured.hide()
                    }
                }
                is NetworkResult.Error -> binding.cardFeatured.hide()
            }
        }
    }

    private fun setupClickListeners() {
        // NavOptions to single-top pop back to home destination
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.homeFragment, false)
            .setLaunchSingleTop(true)
            .build()

        binding.btnExploreTopics.setOnClickListener {
            findNavController().navigate(R.id.topicListFragment, null, navOptions)
        }
        binding.btnWatchVideos.setOnClickListener {
            findNavController().navigate(R.id.videoListFragment, null, navOptions)
        }
        binding.btnLeaderboard.setOnClickListener {
            findNavController().navigate(R.id.leaderboardFragment, null, navOptions)
        }
        binding.btnCarbonCalculator.setOnClickListener {
            findNavController().navigate(R.id.carbonCalculatorFragment, null, navOptions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}