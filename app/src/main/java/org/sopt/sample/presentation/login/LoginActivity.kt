package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.data.local.UiState
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.login.LoginViewModel.Companion.INCORRECT_EMAIL_CODE
import org.sopt.sample.presentation.login.LoginViewModel.Companion.INCORRECT_PWD_CODE
import org.sopt.sample.presentation.login.LoginViewModel.Companion.LOGIN_FAIL_CODE
import org.sopt.sample.presentation.main.MainActivity
import org.sopt.sample.presentation.signup.SignUpActivity
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.extension.hideKeyboard
import org.sopt.sample.util.extension.showSnackbar
import org.sopt.sample.util.extension.showToast

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        signupBtnOnClick()
        observeStateMessage()
    }

    private fun initHideKeyboard() {
        binding.layout.setOnClickListener { this.hideKeyboard() }
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeStateMessage() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                is UiState.Success -> {
                    showToast(getString(R.string.msg_login_success))
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is UiState.Failure -> {
                    when (it.code) {
                        INCORRECT_EMAIL_CODE -> showSnackbar(
                            binding.root,
                            getString(R.string.msg_email_incorrect)
                        )
                        INCORRECT_PWD_CODE -> showSnackbar(
                            binding.root,
                            getString(R.string.msg_pwd_incorrect)
                        )
                        LOGIN_FAIL_CODE -> showSnackbar(
                            binding.root,
                            getString(R.string.msg_login_fail)
                        )
                    }
                }
                is UiState.Error -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }
}
