package org.sopt.sample.presentation.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentSettingBinding
import org.sopt.sample.presentation.login.LoginActivity
import org.sopt.sample.util.binding.BindingFragment

class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutBtnOnClick()
    }

    private fun logoutBtnOnClick() {
        // 로그아웃 버튼을 클릭한 경우
        binding.btnLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }
}
