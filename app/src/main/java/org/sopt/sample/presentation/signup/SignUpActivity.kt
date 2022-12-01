package org.sopt.sample.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.data.local.State
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.binding.BindingActivity
import org.sopt.sample.util.hideKeyboard
import org.sopt.sample.util.showSnackbar
import org.sopt.sample.util.showToast

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        signupBtnOnClick()
        observeStateMessage()
        observeEditTextValidity()
    }

    private fun initHideKeyboard() {
        binding.layout.setOnClickListener { this.hideKeyboard() }
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            viewModel.signup(
                binding.etEmail.text.toString(),
                binding.etPwd.text.toString(),
                binding.etName.text.toString()
            )
        }
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

    private fun observeEditTextValidity() {
        viewModel.isValidEmail.observe(this) {
            checkSingupBtn()
        }
        viewModel.isValidPwd.observe(this) {
            checkSingupBtn()
        }
        viewModel.isValidName.observe(this) {
            checkSingupBtn()
        }
    }

    private fun checkSingupBtn() {
        binding.btnSignup.isEnabled =
            viewModel.isValidEmail.value == true && viewModel.isValidPwd.value == true && viewModel.isValidName.value == true
    }
}
