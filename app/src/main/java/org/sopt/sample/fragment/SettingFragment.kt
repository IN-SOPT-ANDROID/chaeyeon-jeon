package org.sopt.sample.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.LoginActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding
        get() = requireNotNull(_binding) { "binding value was null." }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutBtnOnClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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