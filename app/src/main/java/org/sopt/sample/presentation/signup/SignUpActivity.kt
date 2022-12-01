package org.sopt.sample.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.data.local.State
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.extension.hideKeyboard
import org.sopt.sample.util.extension.showSnackbar
import org.sopt.sample.util.extension.showToast

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
                State.SUCCESS -> {
                    showToast(getString(R.string.msg_signup_success))
                    finish()
                }
                State.FAIL -> showSnackbar(binding.root, getString(R.string.msg_signup_fail))
                State.SERVER_ERROR -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
                else -> showSnackbar(binding.root, getString(R.string.msg_unknown_error))
            }
        }
    }
}
