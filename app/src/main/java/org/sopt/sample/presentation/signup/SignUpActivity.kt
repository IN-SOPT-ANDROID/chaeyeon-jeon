package org.sopt.sample.presentation.signup

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import org.sopt.sample.R
import org.sopt.sample.base.hideKeyboard
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.base.showToast
import org.sopt.sample.data.remote.RequestSignupDto
import org.sopt.sample.data.remote.ResponseSignupDto
import org.sopt.sample.data.remote.ServicePool.authService
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

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
            // 회원가입 API 연결
            authService.signup(
                RequestSignupDto(
                    binding.etEmail.text.toString(),
                    binding.etPwd.text.toString(),
                    binding.etName.text.toString()
                )
            ).enqueue(object : Callback<ResponseSignupDto> {
                override fun onResponse(
                    call: Call<ResponseSignupDto>,
                    response: Response<ResponseSignupDto>
                ) {
                    // signup success
                    if (response.isSuccessful) {
                        showToast(getString(R.string.msg_signup_success))
                        Log.d("SIGNUP_SUCCESS", "response : " + response.body().toString())
                        finish()
                    }
                    // signup fail
                    else {
                        showSnackbar(binding.root, getString(R.string.msg_signup_fail))
                        Log.e("SIGNUP_FAIL", "code : " + response.code())
                        Log.e("SIGNUP_FAIL", "message : " + response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                    showSnackbar(binding.root, getString(R.string.msg_error))
                    Log.e("SIGNUP_FAIL", "message : " + t.message)
                }
            })
        }
    }
}