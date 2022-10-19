package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.data.User
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        // 회원정보 받아오기
        if (intent.hasExtra("user")) {
            val savedUser = intent.getSerializableExtra("user") as User
            binding.txtName.append(savedUser.id)
            binding.txtMbti.append(savedUser.mbti)
        }
    }
}