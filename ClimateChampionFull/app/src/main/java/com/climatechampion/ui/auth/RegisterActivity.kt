package com.climatechampion.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.climatechampion.MainActivity
import com.climatechampion.R
import com.climatechampion.databinding.ActivityRegisterBinding
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.isValidEmail
import com.climatechampion.utils.show
import com.climatechampion.viewmodel.AuthViewModel

/**
 * Registration screen for new users.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeAuthState()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener { attemptRegister() }

        binding.tvLogin.setOnClickListener { finish() }
    }

    private fun attemptRegister() {
        val name = binding.etName.text?.toString()?.trim() ?: ""
        val email = binding.etEmail.text?.toString()?.trim() ?: ""
        val password = binding.etPassword.text?.toString() ?: ""
        val confirm = binding.etConfirmPassword.text?.toString() ?: ""

        // Clear errors
        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null
        binding.tvError.hide()

        var valid = true
        if (name.length < 2) {
            binding.tilName.error = getString(R.string.name_too_short)
            valid = false
        }
        if (!email.isValidEmail()) {
            binding.tilEmail.error = getString(R.string.enter_valid_email)
            valid = false
        }
        if (password.length < 8) {
            binding.tilPassword.error = getString(R.string.password_min_length)
            valid = false
        }
        if (password != confirm) {
            binding.tilConfirmPassword.error = getString(R.string.passwords_do_not_match)
            valid = false
        }
        if (valid) viewModel.register(name, email, password)
    }

    private fun observeAuthState() {
        viewModel.authState.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.btnRegister.isEnabled = false
                }
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    binding.btnRegister.isEnabled = true
                    binding.tvError.text = result.message
                    binding.tvError.show()
                }
            }
        }
    }
}
