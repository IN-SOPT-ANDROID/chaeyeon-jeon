package org.sopt.sample.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import org.sopt.sample.R
import org.sopt.sample.base.hideKeyboard
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.base.showToast
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        checkEditText()
        signupBtnOnClick()
    }

    private fun initView() {
        // 키보드 내리기
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

            viewModel.errorMessage.observe(this) {
                showSnackbar(binding.root, it)
            }
        }
    }
}
