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
import org.sopt.sample.util.hideKeyboard
import org.sopt.sample.util.showSnackbar
import org.sopt.sample.util.showToast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = viewModel

        initHideKeyboard()
        loginBtnOnClick()
        signupBtnOnClick()
        observeLoginResult()
        observeErrorMessage()
    }

    private fun initHideKeyboard() {
        binding.layout.setOnClickListener { this.hideKeyboard() }
    }

    private fun loginBtnOnClick() {
        binding.btnLogin.setOnClickListener {
            // 이메일을 입력하지 않은 경우
            if (binding.etEmail.text.isEmpty()) {
                showSnackbar(binding.root, getString(R.string.msg_email_incorrect))
                return@setOnClickListener
            }

            // 잘못된 비밀번호를 입력한 경우 (성공 조건 : 6자 이상 10자 이하)
            if (binding.etPwd.text.length !in 6..10) {
                showSnackbar(binding.root, getString(R.string.msg_pwd_incorrect))
                return@setOnClickListener
            }

            viewModel.login(binding.etEmail.text.toString(), binding.etPwd.text.toString())
        }
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeErrorMessage() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                State.SUCCESS -> showToast(getString(R.string.msg_login_success))
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
