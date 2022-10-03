package org.sopt.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 완료 버튼을 클릭한 경우
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", binding.etId.text.toString())
            intent.putExtra("password", binding.etPwd.text.toString())
            intent.putExtra("mbti", binding.etMbti.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}