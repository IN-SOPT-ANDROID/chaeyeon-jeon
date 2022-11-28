package org.sopt.sample.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import org.sopt.sample.R
import org.sopt.sample.data.local.State
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.hideKeyboard
import org.sopt.sample.util.showSnackbar
import org.sopt.sample.util.showToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initHideKeyboard()
        checkEditText()
        signupBtnOnClick()
        observeStateMessage()
    }

    private fun initHideKeyboard() {
        binding.layout.setOnClickListener { this.hideKeyboard() }
    }

    private fun checkEditText() {
        binding.etEmail.addTextChangedListener { checkSignupBtn() }
        binding.etPwd.addTextChangedListener { checkSignupBtn() }
        binding.etName.addTextChangedListener { checkSignupBtn() }
    }

    private fun checkSignupBtn() {
        if (binding.etEmail.text.isEmpty() || binding.etPwd.text.length !in 8..12 || binding.etName.text.isEmpty()) {
            binding.btnSignup.isEnabled = false
            return
        }
        binding.btnSignup.isEnabled = true
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            viewModel.signupResult.observe(this) {
                showToast(getString(R.string.msg_signup_success))
                finish()
            }
        }
    }

    private fun observeStateMessage() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                State.SUCCESS -> showToast(getString(R.string.msg_signup_success))
                State.FAIL -> showSnackbar(binding.root, getString(R.string.msg_signup_fail))
                State.SERVER_ERROR -> showSnackbar(binding.root, getString(R.string.msg_server_error))
                else -> showSnackbar(binding.root, getString(R.string.msg_unknown_error))
            }
        }
    }
}
