package org.sopt.sample.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.base.showToast
import org.sopt.sample.data.remote.ResponseGetFollowerListDTO
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
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        // 로그인 id 받아오기
        val intent = activity?.intent
        if (intent != null && intent.hasExtra("id")) {
            val savedUserId = intent.getLongExtra("id", -1L)
            if (savedUserId == -1L) context?.showSnackbar(binding.root, getString(R.string.msg_error))
        }
    }

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}