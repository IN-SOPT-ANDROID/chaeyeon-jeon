package org.sopt.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var savedId: String? = null
    private var savedPwd: String? = null
    private var savedMbti: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 화면으로 돌아올 경우 회원정보 설정 (Activity Result 콜백 등록)
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val id = result.data?.getStringExtra("id")
                val password = result.data?.getStringExtra("password")
                val mbti = result.data?.getStringExtra("mbti")
                savedId = id
                savedPwd = password
                savedMbti = mbti
                Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }

        // 회원가입 버튼을 클릭한 경우
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        // 로그인 버튼을 클릭한 경우
        binding.btnLogin.setOnClickListener {
            // 회원정보가 존재하지 않는 경우
            if (savedId == null || savedPwd == null || savedMbti == null) {
                Toast.makeText(this, "회원정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 입력한 정보가 회원정보와 불일치하는 경우
            if (savedId?.equals(binding.etId.text.toString()) == false || savedPwd?.equals(binding.etPwd.text.toString()) == false) {
                Toast.makeText(this, "잘못된 입력입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id", savedId)
            intent.putExtra("password", savedPwd)
            intent.putExtra("mbti", savedMbti)
            startActivity(intent)
            finish()
        }
    }
}