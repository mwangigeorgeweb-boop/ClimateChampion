package com.climatechampion.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.climatechampion.R
import com.climatechampion.databinding.FragmentProfileBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.ui.auth.LoginActivity
import com.climatechampion.utils.PointsCalculator
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.utils.toast
import com.climatechampion.utils.toFormattedPoints
import com.climatechampion.viewmodel.AuthViewModel
import com.climatechampion.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * Profile screen showing user stats, level progress, and logout.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindIdentity()
        setupObservers()
        setupLogout()
        profileViewModel.loadProfile()
    }

    /**
     * Shows the signed-in user's name and email straight from Firebase Auth, so they appear
     * even if the Firestore profile document is missing or fails to load.
     */
    private fun bindIdentity(fallbackName: String? = null, fallbackEmail: String? = null) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val email = firebaseUser?.email?.takeIf { it.isNotBlank() }
            ?: fallbackEmail?.takeIf { it.isNotBlank() }
        val name = firebaseUser?.displayName?.takeIf { it.isNotBlank() }
            ?: fallbackName?.takeIf { it.isNotBlank() }
            ?: championNameFromEmail(email)

        binding.tvName.text = name
        binding.tvEmail.text = email ?: "Not signed in"
    }

    /** "john.doe@gmail.com" -> "John", "jsmith123@gmail.com" -> "Jsmith", no email -> "Champion". */
    private fun championNameFromEmail(email: String?): String {
        val local = email?.substringBefore('@').orEmpty()
        val first = local.split('.', '_', '-', '+').firstOrNull { it.isNotBlank() }.orEmpty()
        val letters = first.takeWhile { it.isLetter() }
        val name = if (letters.length >= 2) letters else first.ifBlank { "Champion" }
        return name.replaceFirstChar { it.uppercase() }
    }

    private fun setupObservers() {
        profileViewModel.user.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val user = result.data
                    val firebaseUser = FirebaseAuth.getInstance().currentUser


                    // Fetch profile image and upscale Google avatar resolution like Chrome does
                    val rawPhotoUrl = firebaseUser?.photoUrl?.toString()?.takeIf { it.isNotBlank() } ?: user.photoUrl.takeIf { !it.isNullOrBlank() } ?: ""
                    val photoUrl = if (rawPhotoUrl.contains("googleusercontent.com")) {
                        rawPhotoUrl.replace(Regex("=s\\d+-c"), "=s400-c")
                    } else {
                        rawPhotoUrl
                    }

                    bindIdentity(fallbackName = user.displayName, fallbackEmail = user.email)
                    binding.tvPoints.text = getString(
                        R.string.points_format, user.totalPoints.toFormattedPoints()
                    )
                    binding.tvLevel.text = user.level
                    binding.tvLevelEmoji.text = PointsCalculator.levelEmoji(user.level)
                    binding.progressLevel.progress =
                        PointsCalculator.progressToNextLevel(user.totalPoints)
                    val toNext = PointsCalculator.pointsToNextLevel(user.totalPoints)
                    binding.tvNextLevel.text = if (toNext > 0) {
                        getString(R.string.points_to_next_level, toNext)
                    } else {
                        getString(R.string.max_level_reached)
                    }

                    // Load high-res profile picture using Glide
                    if (photoUrl.isNotBlank()) {
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
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    bindIdentity() // name/email still come from Firebase Auth
                    toast(result.message) // shows WHY the points/stats could not load
                }
            }
        }

        profileViewModel.progress.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val progressList = result.data
                val completed = progressList.count { it.completed }
                val inProgress = progressList.count { !it.completed && it.percentComplete > 0 }
                binding.tvTopicsCompleted.text = completed.toString()
                binding.tvTopicsInProgress.text = inProgress.toString()
            }
        }

        profileViewModel.quizResults.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val quizzes = result.data
                binding.tvQuizzesTaken.text = quizzes.size.toString()
                val passed = quizzes.count { it.passed }
                binding.tvQuizzesPassed.text = passed.toString()
                val avgScore = if (quizzes.isNotEmpty()) {
                    quizzes.map { it.percentage }.average().toInt()
                } else 0
                binding.tvAvgScore.text = getString(R.string.percent_format, avgScore)
            }
        }
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {
            authViewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}