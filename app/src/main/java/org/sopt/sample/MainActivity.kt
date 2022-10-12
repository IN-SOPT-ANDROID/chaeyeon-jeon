package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.fragment.HomeFragment
import org.sopt.sample.fragment.SettingFragment
import org.sopt.sample.fragment.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fragmentHome by lazy { HomeFragment() }
    private val fragmentUser by lazy { UserFragment() }
    private val fragmentSetting by lazy { SettingFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container_home)
        if (currentFragment == null) setCurrentFragment(fragmentHome)

        binding.bnvHome.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> setCurrentFragment(fragmentHome)
                R.id.menu_user -> setCurrentFragment(fragmentUser)
                R.id.menu_setting -> setCurrentFragment(fragmentSetting)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_home, fragment)
            .commit()
    }
}