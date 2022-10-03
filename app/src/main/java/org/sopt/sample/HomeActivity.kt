package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원정보 받아오기
        if (intent.hasExtra("id")) {
            binding.txtName.text = binding.txtName.text.toString() + intent.getStringExtra("id");
        }
        if (intent.hasExtra("mbti")) {
            binding.txtMbti.text = binding.txtMbti.text.toString() + intent.getStringExtra("mbti");
        }
    }
}