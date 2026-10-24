package com.climatechampion.ui.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.climatechampion.databinding.FragmentTopicListBinding
import com.climatechampion.model.Topic
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.viewmodel.TopicViewModel

/**
 * Fragment showing the full list of climate topics with filter controls.
 */
class TopicListFragment : Fragment() {

    private var _binding: FragmentTopicListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TopicViewModel by viewModels()

    private lateinit var topicAdapter: TopicAdapter
    private var allTopics: List<Topic> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFilters()
        setupObservers()
        setupSwipeRefresh()
        viewModel.loadAllTopics()
        viewModel.loadUserProgress()
    }

    private fun setupRecyclerView() {
        topicAdapter = TopicAdapter { topic ->
            val action = TopicListFragmentDirections
                .actionTopicListFragmentToTopicDetailFragment(topic.topicId)
            findNavController().navigate(action)
        }
        binding.rvTopics.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = topicAdapter
        }
    }

    private fun setupFilters() {
        // Category spinner
        val categories = listOf("All Categories", "Carbon", "Energy", "Forests",
            "Oceans", "Policy", "Air", "Biodiversity", "Water", "Gases", "Lifestyle")
        val categoryAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, categories
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        binding.spinnerCategory.adapter = categoryAdapter

        // Difficulty spinner
        val difficulties = listOf("All Levels", "Beginner", "Intermediate", "Expert")
        val diffAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, difficulties
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        binding.spinnerDifficulty.adapter = diffAdapter

        binding.btnFilter.setOnClickListener { applyFilters() }
    }

    private fun applyFilters() {
        val selectedCategory = binding.spinnerCategory.selectedItem?.toString() ?: "All Categories"
        val selectedDifficulty = binding.spinnerDifficulty.selectedItem?.toString() ?: "All Levels"

        val filtered = allTopics.filter { topic ->
            val categoryMatch = selectedCategory == "All Categories" ||
                    topic.category.contains(selectedCategory, ignoreCase = true)
            val difficultyMatch = selectedDifficulty == "All Levels" ||
                    topic.difficulty.equals(selectedDifficulty, ignoreCase = true)
            categoryMatch && difficultyMatch
        }

        topicAdapter.submitList(filtered)
        binding.tvEmptyState.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun setupObservers() {
        viewModel.topics.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.tvEmptyState.hide()
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.swipeRefresh.isRefreshing = false
                    val topicsList = result.data
                    allTopics = topicsList
                    topicAdapter.submitList(topicsList)
                    binding.tvEmptyState.visibility =
                        if (topicsList.isEmpty()) View.VISIBLE else View.GONE
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.swipeRefresh.isRefreshing = false
                    binding.tvEmptyState.text = result.message
                    binding.tvEmptyState.show()
                }
            }
        }

        viewModel.userProgress.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                topicAdapter.setProgress(result.data)
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadAllTopics()
            viewModel.loadUserProgress()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}