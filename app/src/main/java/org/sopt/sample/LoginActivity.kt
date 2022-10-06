package org.sopt.sample

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.base.showToast
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
                savedId = result.data?.getStringExtra("id")
                savedPwd = result.data?.getStringExtra("password")
                savedMbti = result.data?.getStringExtra("mbti")
                showSnackbar(binding.root, getString(R.string.msg_signup_success))
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
            if (savedId == null || savedPwd == null) {
                showToast(getString(R.string.msg_login_null))
                return@setOnClickListener
            }

            // 입력한 정보가 회원정보와 불일치하는 경우
            if (savedId != binding.etId.text.toString() || savedPwd != binding.etPwd.text.toString()) {
                showToast(getString(R.string.msg_incorrect))
                return@setOnClickListener
            }

            // 로그인 성공
            showToast(getString(R.string.msg_login_success))
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("id", savedId)
                putExtra("password", savedPwd)
                putExtra("mbti", savedMbti)
            }
            startActivity(intent)
            finish()
        }
    }
}