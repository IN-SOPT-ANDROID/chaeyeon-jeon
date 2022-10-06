package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                intent.putExtra("id", binding.etId.text.toString())
                intent.putExtra("password", binding.etPwd.text.toString())
                intent.putExtra("mbti", binding.etMbti.text.toString())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}