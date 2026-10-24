package com.climatechampion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatechampion.model.QuizResult
import com.climatechampion.model.User
import com.climatechampion.model.UserProgress
import com.climatechampion.network.NetworkResult
import com.climatechampion.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

/**
 * ViewModel for the profile screen.
 */
class ProfileViewModel : ViewModel() {

    private val firestoreRepo = FirestoreRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _user = MutableLiveData<NetworkResult<User>>()
    val user: LiveData<NetworkResult<User>> = _user

    private val _progress = MutableLiveData<NetworkResult<List<UserProgress>>>()
    val progress: LiveData<NetworkResult<List<UserProgress>>> = _progress

    private val _quizResults = MutableLiveData<NetworkResult<List<QuizResult>>>()
    val quizResults: LiveData<NetworkResult<List<QuizResult>>> = _quizResults

    fun loadProfile() {
        val userId = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            _user.value = NetworkResult.Loading()
            _user.value = firestoreRepo.getUser(userId)

            _progress.value = NetworkResult.Loading()
            _progress.value = firestoreRepo.getUserProgress(userId)

            _quizResults.value = NetworkResult.Loading()
            _quizResults.value = firestoreRepo.getQuizResults(userId)
        }
    }
}
