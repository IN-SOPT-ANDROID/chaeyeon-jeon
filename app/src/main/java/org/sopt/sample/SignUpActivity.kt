package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.base.hideKeyboard
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.data.User
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        signupBtnOnClick()
    }

    private fun init() {
        // 키보드 내리기
        binding.layout.setOnClickListener { this.hideKeyboard() }
    }

    private fun signupBtnOnClick() {
        // 회원가입 완료 버튼을 클릭한 경우
        binding.btnSignup.setOnClickListener {
            // 잘못된 아이디가 입력된 경우 (성공조건 : 6 ~ 10글자)
            if (binding.etId.text.length !in 6..10) {
                showSnackbar(binding.root, getString(R.string.msg_id_incorrect))
                return@setOnClickListener
            }

            // 잘못된 비밀번호가 입력된 경우 (성공조건 : 8 ~ 12글자)
            if (binding.etPwd.text.length !in 8..12) {
                showSnackbar(binding.root, getString(R.string.msg_pwd_incorrect))
                return@setOnClickListener
            }

            // 회원가입 성공
            val intent = Intent(this, LoginActivity::class.java).apply {
                val user = User(
                    R.drawable.ic_profile,
                    binding.etId.text.toString(),
                    binding.etPwd.text.toString(),
                    binding.etMbti.text.toString()
                )
                intent.putExtra("user", user as java.io.Serializable)
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}