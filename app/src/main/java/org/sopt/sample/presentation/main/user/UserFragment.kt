package org.sopt.sample.presentation.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentUserBinding
import org.sopt.sample.util.binding.BindingFragment

class UserFragment : BindingFragment<FragmentUserBinding>(R.layout.fragment_user) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}
