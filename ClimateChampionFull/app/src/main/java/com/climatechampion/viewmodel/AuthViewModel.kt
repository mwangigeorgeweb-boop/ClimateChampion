package com.climatechampion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatechampion.model.User
import com.climatechampion.network.NetworkResult
import com.climatechampion.repository.FirestoreRepository
import com.climatechampion.utils.Constants
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * ViewModel for authentication screens (Login, Register, Splash).
 * Handles Firebase Auth operations and exposes state via LiveData.
 */
class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestoreRepo = FirestoreRepository()

    private val _authState = MutableLiveData<NetworkResult<FirebaseUser>>()
    val authState: LiveData<NetworkResult<FirebaseUser>> = _authState

    private val _logoutState = MutableLiveData<Boolean>()
    val logoutState: LiveData<Boolean> = _logoutState

    /** True if a user is currently signed in. */
    val isLoggedIn: Boolean get() = auth.currentUser != null

    val currentUserId: String? get() = auth.currentUser?.uid

    // ─── Email / Password Auth ────────────────────────────────────────────────

    /**
     * Registers a new user with email and password, then creates their Firestore profile.
     */
    fun register(displayName: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = NetworkResult.Loading()
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val firebaseUser = result.user
                    ?: run {
                        _authState.value = NetworkResult.Error("Registration failed")
                        return@launch
                    }

                // Create Firestore user document
                val user = User(
                    userId = firebaseUser.uid,
                    displayName = displayName,
                    email = email,
                    photoUrl = firebaseUser.photoUrl?.toString() ?: "",
                    totalPoints = Constants.POINTS_DAILY_LOGIN, // Award first login points
                    level = Constants.LEVEL_BEGINNER,
                    createdAt = Timestamp.now(),
                    lastActive = Timestamp.now()
                )
                firestoreRepo.createOrUpdateUser(user)
                _authState.value = NetworkResult.Success(firebaseUser)
            } catch (e: Exception) {
                _authState.value = NetworkResult.Error(e.message ?: "Registration failed")
            }
        }
    }

    /**
     * Signs in an existing user with email and password.
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = NetworkResult.Loading()
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                val firebaseUser = result.user
                    ?: run {
                        _authState.value = NetworkResult.Error("Login failed")
                        return@launch
                    }
                firestoreRepo.updateLastActive(firebaseUser.uid)
                _authState.value = NetworkResult.Success(firebaseUser)
            } catch (e: Exception) {
                _authState.value = NetworkResult.Error(e.message ?: "Login failed")
            }
        }
    }

    /**
     * Signs in with a Google credential obtained from Google Sign-In flow.
     */
    fun signInWithCredential(credential: AuthCredential) {
        viewModelScope.launch {
            _authState.value = NetworkResult.Loading()
            try {
                val result = auth.signInWithCredential(credential).await()
                val firebaseUser = result.user
                    ?: run {
                        _authState.value = NetworkResult.Error("Google sign-in failed")
                        return@launch
                    }

                // Create Firestore profile if first sign-in
                if (result.additionalUserInfo?.isNewUser == true) {
                    val user = User(
                        userId = firebaseUser.uid,
                        displayName = firebaseUser.displayName ?: "Champion",
                        email = firebaseUser.email ?: "",
                        photoUrl = firebaseUser.photoUrl?.toString() ?: "",
                        totalPoints = Constants.POINTS_DAILY_LOGIN,
                        level = Constants.LEVEL_BEGINNER,
                        createdAt = Timestamp.now(),
                        lastActive = Timestamp.now()
                    )
                    firestoreRepo.createOrUpdateUser(user)
                } else {
                    firestoreRepo.updateLastActive(firebaseUser.uid)
                }

                _authState.value = NetworkResult.Success(firebaseUser)
            } catch (e: Exception) {
                _authState.value = NetworkResult.Error(e.message ?: "Google sign-in failed")
            }
        }
    }

    /**
     * Signs the current user out.
     */
    fun logout() {
        auth.signOut()
        _logoutState.value = true
    }
}
