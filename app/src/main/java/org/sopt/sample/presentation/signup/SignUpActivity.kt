package org.sopt.sample.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.util.UiState
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.extension.hideKeyboard
import org.sopt.sample.util.extension.showSnackbar
import org.sopt.sample.util.extension.showToast

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        observeStateMessage()
    }

    private fun initHideKeyboard() {
        binding.layout.setOnClickListener { this.hideKeyboard() }
    }

    private fun observeStateMessage() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                is UiState.Success -> {
                    showToast(getString(R.string.msg_signup_success))
                    finish()
                }
                is UiState.Failure -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_signup_fail)
                )
                is UiState.Error -> showSnackbar(binding.root, getString(R.string.msg_server_error))
            }
        }
    }
}
