package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.data.local.State
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.signup.SignUpActivity
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.extension.hideKeyboard
import org.sopt.sample.util.extension.showSnackbar
import org.sopt.sample.util.extension.showToast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        signupBtnOnClick()
        observeLoginResult()
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
                State.SUCCESS -> showToast(getString(R.string.msg_login_success))
                State.LOGIN_INCORRECT_EMAIL -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_email_incorrect)
                )
                State.LOGIN_INCORRECT_PWD -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_pwd_incorrect)
                )
                State.FAIL -> showSnackbar(binding.root, getString(R.string.msg_login_fail))
                State.SERVER_ERROR -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
                else -> showSnackbar(binding.root, getString(R.string.msg_unknown_error))
            }
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(this) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                putExtra("id", it.result?.id)
            }
            startActivity(intent)
            finish()
        }
    }
}
