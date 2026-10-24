package com.climatechampion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatechampion.model.Subtopic
import com.climatechampion.model.Topic
import com.climatechampion.model.UserProgress
import com.climatechampion.network.NetworkResult
import com.climatechampion.repository.ApiRepository
import com.climatechampion.repository.FirestoreRepository
import com.climatechampion.utils.Constants
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

/**
 * ViewModel for topic list and topic detail screens.
 */
class TopicViewModel : ViewModel() {

    private val apiRepo = ApiRepository()
    private val firestoreRepo = FirestoreRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _topics = MutableLiveData<NetworkResult<List<Topic>>>()
    val topics: LiveData<NetworkResult<List<Topic>>> = _topics

    private val _selectedTopic = MutableLiveData<NetworkResult<Topic>>()
    val selectedTopic: LiveData<NetworkResult<Topic>> = _selectedTopic

    private val _subtopics = MutableLiveData<NetworkResult<List<Subtopic>>>()
    val subtopics: LiveData<NetworkResult<List<Subtopic>>> = _subtopics

    private val _userProgress = MutableLiveData<NetworkResult<List<UserProgress>>>()
    val userProgress: LiveData<NetworkResult<List<UserProgress>>> = _userProgress

    private val _topicCompleteResult = MutableLiveData<NetworkResult<Unit>>()
    val topicCompleteResult: LiveData<NetworkResult<Unit>> = _topicCompleteResult

    // ─── Fetch Topics ─────────────────────────────────────────────────────────

    fun loadAllTopics() {
        viewModelScope.launch {
            _topics.value = NetworkResult.Loading()
            _topics.value = apiRepo.getAllTopics()
        }
    }

    fun loadTopicById(topicId: String) {
        viewModelScope.launch {
            _selectedTopic.value = NetworkResult.Loading()
            _selectedTopic.value = apiRepo.getTopicById(topicId)
        }
    }

    fun loadSubtopics(topicId: String) {
        viewModelScope.launch {
            _subtopics.value = NetworkResult.Loading()
            _subtopics.value = apiRepo.getSubtopics(topicId)
        }
    }

    // ─── Progress ─────────────────────────────────────────────────────────────

    fun loadUserProgress() {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            _userProgress.value = NetworkResult.Loading()
            _userProgress.value = firestoreRepo.getUserProgress(userId)
        }
    }

    /**
     * Marks a topic as complete, saves progress, and awards points.
     */
    fun markTopicComplete(topicId: String) {
        val userId = auth.currentUser?.uid ?: return
        val user = auth.currentUser ?: return
        viewModelScope.launch {
            _topicCompleteResult.value = NetworkResult.Loading()
            try {
                // Save progress
                val progress = UserProgress(
                    topicId = topicId,
                    completed = true,
                    percentComplete = 100,
                    lastUpdated = Timestamp.now()
                )
                firestoreRepo.updateProgress(userId, progress)

                // Award points
                firestoreRepo.addPoints(userId, Constants.POINTS_TOPIC_COMPLETE)

                // Update leaderboard
                firestoreRepo.updateLeaderboard(
                    userId = userId,
                    displayName = user.displayName ?: "Champion",
                    photoUrl = user.photoUrl?.toString() ?: "",
                    pointsEarned = Constants.POINTS_TOPIC_COMPLETE,
                    quizPassed = false
                )

                _topicCompleteResult.value = NetworkResult.Success(Unit)
            } catch (e: Exception) {
                _topicCompleteResult.value = NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Updates partial progress for a topic (e.g., reading a subtopic).
     */
    fun updateProgress(topicId: String, percentComplete: Int) {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            val progress = UserProgress(
                topicId = topicId,
                completed = percentComplete >= 100,
                percentComplete = percentComplete,
                lastUpdated = Timestamp.now()
            )
            firestoreRepo.updateProgress(userId, progress)
        }
    }
}
