package com.climatechampion.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.climatechampion.databinding.FragmentVideoListBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.viewmodel.VideoViewModel

/**
 * Fragment displaying all available climate videos in a 2-column grid.
 */
class VideoListFragment : Fragment() {

    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VideoViewModel by viewModels()

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupSwipeRefresh()
        viewModel.loadAllVideos()
    }

    private fun setupRecyclerView() {
        videoAdapter = VideoAdapter { video ->
            val action = VideoListFragmentDirections
                .actionVideoListFragmentToVideoPlayerFragment(video.videoId)
            findNavController().navigate(action)
        }
        binding.rvVideos.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = videoAdapter
        }
    }

    private fun setupObservers() {
        viewModel.videos.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.tvEmpty.hide()
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.swipeRefresh.isRefreshing = false
                    val videos = result.data
                    videoAdapter.submitList(videos)
                    binding.tvEmpty.visibility =
                        if (videos.isEmpty()) View.VISIBLE else View.GONE
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.swipeRefresh.isRefreshing = false
                    binding.tvEmpty.text = result.message
                    binding.tvEmpty.show()
                }
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadAllVideos()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}