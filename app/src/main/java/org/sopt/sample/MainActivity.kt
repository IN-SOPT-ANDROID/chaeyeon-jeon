package org.sopt.sample

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.fragment.HomeFragment
import org.sopt.sample.fragment.SettingFragment
import org.sopt.sample.fragment.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        // 시작 프래그먼트 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container_home)
        if (currentFragment == null) setCurrentFragment<HomeFragment>()

        // 메뉴를 선택한 경우
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> setCurrentFragment<HomeFragment>()
                R.id.menu_user -> setCurrentFragment<UserFragment>()
                R.id.menu_setting -> setCurrentFragment<SettingFragment>()
            }
            true
        }
    }

    private inline fun <reified T: Fragment> setCurrentFragment() {
        supportFragmentManager.commit {
            replace<T>(R.id.container_home, T::class.java.canonicalName)
        }
    }
}