package com.climatechampion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatechampion.model.Video
import com.climatechampion.network.NetworkResult
import com.climatechampion.repository.ApiRepository
import com.climatechampion.repository.FirestoreRepository
import com.climatechampion.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

/**
 * ViewModel for video list and video player screens.
 */
class VideoViewModel : ViewModel() {

    private val apiRepo = ApiRepository()
    private val firestoreRepo = FirestoreRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _videos = MutableLiveData<NetworkResult<List<Video>>>()
    val videos: LiveData<NetworkResult<List<Video>>> = _videos

    private val _selectedVideo = MutableLiveData<NetworkResult<Video>>()
    val selectedVideo: LiveData<NetworkResult<Video>> = _selectedVideo

    private val _relatedVideos = MutableLiveData<NetworkResult<List<Video>>>()
    val relatedVideos: LiveData<NetworkResult<List<Video>>> = _relatedVideos

    private val _videoWatchedResult = MutableLiveData<NetworkResult<Unit>>()
    val videoWatchedResult: LiveData<NetworkResult<Unit>> = _videoWatchedResult

    // Track which videos have been marked as watched this session
    private val watchedVideoIds = mutableSetOf<String>()

    // ─── Load Videos ──────────────────────────────────────────────────────────

    fun loadAllVideos() {
        viewModelScope.launch {
            _videos.value = NetworkResult.Loading()
            _videos.value = apiRepo.getAllVideos()
        }
    }

    fun loadVideosByTopic(topicId: String) {
        viewModelScope.launch {
            _videos.value = NetworkResult.Loading()
            _videos.value = apiRepo.getVideosByTopic(topicId)
        }
    }

    fun loadVideoById(videoId: String) {
        viewModelScope.launch {
            _selectedVideo.value = NetworkResult.Loading()
            // Find from cache or fetch all and filter
            val allResult = apiRepo.getAllVideos()
            if (allResult is NetworkResult.Success<List<Video>>) {
                val video = allResult.data.find { it.videoId == videoId }
                _selectedVideo.value = if (video != null) {
                    NetworkResult.Success(video)
                } else {
                    NetworkResult.Error("Video not found")
                }
            } else {
                _selectedVideo.value = NetworkResult.Error("Failed to load video")
            }
        }
    }

    fun loadRelatedVideos(topicId: String) {
        viewModelScope.launch {
            _relatedVideos.value = NetworkResult.Loading()
            _relatedVideos.value = apiRepo.getVideosByTopic(topicId)
        }
    }

    // ─── Mark Watched ─────────────────────────────────────────────────────────

    /**
     * Called when a video reaches the end. Awards points once per video per session.
     */
    fun onVideoCompleted(video: Video) {
        if (watchedVideoIds.contains(video.videoId)) return // Already awarded
        val userId = auth.currentUser?.uid ?: return
        val user = auth.currentUser ?: return

        viewModelScope.launch {
            _videoWatchedResult.value = NetworkResult.Loading()
            try {
                // Mark via API
                apiRepo.markVideoWatched(userId, video.videoId)

                // Award points
                firestoreRepo.addPoints(userId, Constants.POINTS_VIDEO_WATCHED)

                // Update leaderboard
                firestoreRepo.updateLeaderboard(
                    userId = userId,
                    displayName = user.displayName ?: "Champion",
                    photoUrl = user.photoUrl?.toString() ?: "",
                    pointsEarned = Constants.POINTS_VIDEO_WATCHED,
                    quizPassed = false
                )

                watchedVideoIds.add(video.videoId)
                _videoWatchedResult.value = NetworkResult.Success(Unit)
            } catch (e: Exception) {
                _videoWatchedResult.value = NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}
