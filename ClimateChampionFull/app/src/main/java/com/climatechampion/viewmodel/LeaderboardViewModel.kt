package com.climatechampion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatechampion.model.LeaderboardEntry
import com.climatechampion.network.NetworkResult
import com.climatechampion.repository.ApiRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

/**
 * ViewModel for the leaderboard screen.
 * Supports toggling between all-time and weekly views.
 */
class LeaderboardViewModel : ViewModel() {

    private val apiRepo = ApiRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _leaderboard = MutableLiveData<NetworkResult<List<LeaderboardEntry>>>()
    val leaderboard: LiveData<NetworkResult<List<LeaderboardEntry>>> = _leaderboard

    private val _isWeeklyMode = MutableLiveData(false)
    val isWeeklyMode: LiveData<Boolean> = _isWeeklyMode

    val currentUserId: String? get() = auth.currentUser?.uid

    fun loadAllTimeLeaderboard() {
        _isWeeklyMode.value = false
        viewModelScope.launch {
            _leaderboard.value = NetworkResult.Loading()
            _leaderboard.value = apiRepo.getLeaderboard()
        }
    }

    fun loadWeeklyLeaderboard() {
        _isWeeklyMode.value = true
        viewModelScope.launch {
            _leaderboard.value = NetworkResult.Loading()
            _leaderboard.value = apiRepo.getWeeklyLeaderboard()
        }
    }

    fun toggleMode() {
        if (_isWeeklyMode.value == true) loadAllTimeLeaderboard()
        else loadWeeklyLeaderboard()
    }

    fun refresh() {
        if (_isWeeklyMode.value == true) loadWeeklyLeaderboard()
        else loadAllTimeLeaderboard()
    }
}
