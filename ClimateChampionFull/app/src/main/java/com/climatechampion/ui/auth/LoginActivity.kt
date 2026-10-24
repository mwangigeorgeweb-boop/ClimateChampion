package com.climatechampion.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.climatechampion.MainActivity
import com.climatechampion.R
import com.climatechampion.databinding.ActivityLoginBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.isValidEmail
import com.climatechampion.utils.show
import com.climatechampion.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * Login screen supporting email/password and Google Sign-In.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                if (!idToken.isNullOrBlank()) {
                    val credential = GoogleAuthProvider.getCredential(idToken, null)
                    viewModel.signInWithCredential(credential)
                } else {
                    binding.tvError.text = getString(R.string.google_sign_in_failed)
                    binding.tvError.show()
                }
            } catch (e: ApiException) {
                binding.tvError.text = e.localizedMessage ?: getString(R.string.google_sign_in_failed)
                binding.tvError.show()
            }
        } else {
            binding.tvError.text = getString(R.string.google_sign_in_failed)
            binding.tvError.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeAuthState()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener { attemptLogin() }

        binding.btnGoogleSignIn.setOnClickListener { startGoogleSignIn() }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            val email = binding.etEmail.text?.toString()?.trim() ?: ""
            if (email.isValidEmail()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                binding.tvError.text = getString(R.string.password_reset_sent)
                binding.tvError.show()
            } else {
                binding.tilEmail.error = getString(R.string.enter_valid_email)
            }
        }
    }

    private fun attemptLogin() {
        val email = binding.etEmail.text?.toString()?.trim() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""

        // Clear previous errors
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tvError.hide()

        var valid = true
        if (!email.isValidEmail()) {
            binding.tilEmail.error = getString(R.string.enter_valid_email)
            valid = false
        }
        if (password.length < 8) {
            binding.tilPassword.error = getString(R.string.password_min_length)
            valid = false
        }
        if (valid) viewModel.login(email, password)
    }

    private fun startGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(this, gso)
        googleSignInLauncher.launch(client.signInIntent)
    }

    private fun observeAuthState() {
        viewModel.authState.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnLogin.isEnabled = false
                    binding.btnGoogleSignIn.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    navigateToMain()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnLogin.isEnabled = true
                    binding.btnGoogleSignIn.isEnabled = true
                    binding.tvError.text = result.message
                    binding.tvError.show()
                }
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}