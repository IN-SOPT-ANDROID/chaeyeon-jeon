package org.sopt.sample.presentation.main

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.main.home.HomeFragment
import org.sopt.sample.presentation.main.setting.SettingFragment
import org.sopt.sample.presentation.main.user.UserFragment
import org.sopt.sample.util.binding.BindingActivity

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    private inline fun <reified T : Fragment> setCurrentFragment() {
        supportFragmentManager.commit {
            replace<T>(R.id.container_home, T::class.java.canonicalName)
        }
    }
}
