package com.climatechampion.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.climatechampion.MainActivity
import com.climatechampion.R
import com.climatechampion.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Splash screen shown at app launch.
 * Plays logo animation, then navigates to Login or Home based on auth state.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start logo animation
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_scale)
        binding.ivLogo.startAnimation(fadeIn)
        binding.tvAppName.startAnimation(fadeIn)
        binding.tvTagline.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up))

        // Navigate after delay
        lifecycleScope.launch {
            delay(2200L)
            navigateNext()
        }
    }

    private fun navigateNext() {
        val destination = if (auth.currentUser != null) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        startActivity(destination)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
