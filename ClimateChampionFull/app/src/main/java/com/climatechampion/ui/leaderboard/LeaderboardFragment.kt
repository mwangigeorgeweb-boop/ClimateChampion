package com.climatechampion.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.climatechampion.databinding.FragmentLeaderboardBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.viewmodel.LeaderboardViewModel

/**
 * Leaderboard screen with All-Time / Weekly toggle.
 * Highlights the current user's position in the list.
 */
class LeaderboardFragment : Fragment() {

    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LeaderboardViewModel by viewModels()
    private lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupToggle()
        setupObservers()
        setupSwipeRefresh()
        viewModel.loadAllTimeLeaderboard()
    }

    private fun setupRecyclerView() {
        leaderboardAdapter = LeaderboardAdapter(viewModel.currentUserId)
        binding.rvLeaderboard.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = leaderboardAdapter
        }
    }

    private fun setupToggle() {
        binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            when (checkedId) {
                binding.btnAllTime.id -> viewModel.loadAllTimeLeaderboard()
                binding.btnWeekly.id -> viewModel.loadWeeklyLeaderboard()
            }
        }
        binding.btnAllTime.isChecked = true
    }

    private fun setupObservers() {
        viewModel.leaderboard.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.tvEmpty.hide()
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    binding.swipeRefresh.isRefreshing = false
                    val entries = result.data
                    leaderboardAdapter.submitList(entries)
                    binding.tvEmpty.visibility =
                        if (entries.isEmpty()) View.VISIBLE else View.GONE
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
        binding.swipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}