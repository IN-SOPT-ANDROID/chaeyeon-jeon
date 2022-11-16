package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.base.hideKeyboard
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.base.showToast
import org.sopt.sample.data.remote.RequestLoginDto
import org.sopt.sample.data.remote.ResponseLoginDto
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.presentation.signup.SignUpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authService = ServicePool.authService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        loginBtnOnClick()
        signupBtnOnClick()
    }

    private fun initView() {
        // 키보드 내리기
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

            // 로그인 API 연결
            authService.login(
                RequestLoginDto(
                    binding.etEmail.text.toString(),
                    binding.etPwd.text.toString()
                )
            ).enqueue(object : Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>
                ) {
                    // login success
                    if (response.isSuccessful) {
                        showToast(getString(R.string.msg_login_success))
                        Log.d("LOGIN_SUCCESS", "response : " + response.body().toString())
                        val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                            putExtra("id", response.body()?.result?.id)
                        }
                        startActivity(intent)
                        finish()
                    }
                    // login fail
                    else {
                        showSnackbar(binding.root, getString(R.string.msg_login_fail))
                        Log.e("LOGIN_FAIL", "message : " + response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    showSnackbar(binding.root, getString(R.string.msg_error))
                    Log.e("LOGIN_FAIL", "cause : " + t.cause)
                    Log.e("LOGIN_FAIL", "message : " + t.message)
                }
            })
        }
    }

    private fun signupBtnOnClick() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}