package com.climatechampion.ui.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.climatechampion.R
import com.climatechampion.databinding.FragmentTopicDetailBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.ui.video.VideoAdapter
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.utils.toast
import com.climatechampion.viewmodel.TopicViewModel
import com.climatechampion.viewmodel.VideoViewModel

/**
 * Displays the full detail of a single topic:
 * Header details, subtopics (expandable), related videos, and quiz/completion actions.
 */
class TopicDetailFragment : Fragment() {

    private var _binding: FragmentTopicDetailBinding? = null
    private val binding get() = _binding!!

    private val args: TopicDetailFragmentArgs by navArgs()
    private val topicViewModel: TopicViewModel by viewModels()
    private val videoViewModel: VideoViewModel by viewModels()

    private lateinit var subtopicAdapter: SubtopicAdapter
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        setupObservers()

        val topicId = args.topicId
        if (topicId.isNotEmpty()) {
            topicViewModel.loadTopicById(topicId)
            topicViewModel.loadSubtopics(topicId)
            videoViewModel.loadVideosByTopic(topicId)
        }

        binding.btnTakeQuiz.setOnClickListener {
            val action = TopicDetailFragmentDirections
                .actionTopicDetailFragmentToQuizFragment(topicId)
            findNavController().navigate(action)
        }

        binding.btnMarkComplete.setOnClickListener {
            topicViewModel.markTopicComplete(topicId)
        }
    }

    private fun setupRecyclerViews() {
        subtopicAdapter = SubtopicAdapter()
        binding.rvSubtopics.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = subtopicAdapter
            isNestedScrollingEnabled = false
        }

        videoAdapter = VideoAdapter { video ->
            val action = TopicDetailFragmentDirections
                .actionTopicDetailFragmentToVideoPlayerFragment(video.videoId)
            findNavController().navigate(action)
        }
        binding.rvRelatedVideos.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            adapter = videoAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupObservers() {
        topicViewModel.selectedTopic.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.contentGroup.hide()
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.contentGroup.show()
                    val topic = result.data
                    binding.tvTitle.text = topic.title
                    binding.tvDescription.text = topic.description
                    binding.tvCategory.text = topic.category
                    binding.tvDifficulty.text = topic.difficulty

                    binding.tvPoints.text = try {
                        getString(R.string.points_reward_format, topic.pointsReward)
                    } catch (_: Exception) {
                        "+${topic.pointsReward} pts"
                    }

                    if (topic.thumbnailUrl.isNotEmpty()) {
                        Glide.with(this)
                            .load(topic.thumbnailUrl)
                            .placeholder(R.drawable.ic_topic_placeholder)
                            .error(R.drawable.ic_topic_placeholder)
                            .centerCrop()
                            .into(binding.ivHeader)
                    } else {
                        binding.ivHeader.setImageResource(R.drawable.ic_topic_placeholder)
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    toast(result.message)
                }
            }
        }

        topicViewModel.subtopics.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val subtopics = result.data
                subtopicAdapter.submitList(subtopics)
                binding.tvSubtopicsEmpty.visibility =
                    if (subtopics.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        videoViewModel.videos.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val videos = result.data
                videoAdapter.submitList(videos)
                binding.tvRelatedVideosEmpty.visibility =
                    if (videos.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        topicViewModel.topicCompleteResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    toast(getString(R.string.topic_complete_congrats))
                    binding.btnMarkComplete.isEnabled = false
                    binding.btnMarkComplete.text = getString(R.string.completed)
                }
                is NetworkResult.Error -> toast(result.message)
                is NetworkResult.Loading -> binding.btnMarkComplete.isEnabled = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}