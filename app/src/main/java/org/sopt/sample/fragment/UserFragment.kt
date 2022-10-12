package org.sopt.sample.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.data.User
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = requireNotNull(_binding) { "binding value was null." }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        // 회원정보 받아오기
        val intent = activity?.intent
        if (intent != null && intent.hasExtra("user")) {
            val savedUser = intent.getSerializableExtra("user") as User
            binding.txtName.append(savedUser.id)
            binding.txtMbti.append(savedUser.mbti)
        }
    }

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}