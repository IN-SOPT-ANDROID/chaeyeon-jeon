package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import org.sopt.sample.R
import org.sopt.sample.data.local.State
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.home.FollowerAdapter.Companion.VIEW_TYPE_HEADER
import org.sopt.sample.presentation.home.FollowerAdapter.Companion.VIEW_TYPE_ITEM
import org.sopt.sample.util.binding.BindingFragment
import org.sopt.sample.util.showSnackbar

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private val followerAdapter by lazy { FollowerAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getFollowerList()
        observeFollowerList()
        observeStateMessage()
    }

    /** 리사이클러뷰 어댑터 및 레이아웃 매니저 설정 */
    private fun initAdapter() {
        binding.rvFollower.adapter = followerAdapter
        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (followerAdapter.getItemViewType(position)) {
                    VIEW_TYPE_HEADER -> return 2
                    VIEW_TYPE_ITEM -> return 1
                    else -> throw ClassCastException(
                        "Unkown View Type : ${
                        followerAdapter.getItemViewType(
                            position
                        )
                        }"
                    )
                }
            }
        }
        binding.rvFollower.layoutManager = layoutManager
    }

    private fun getFollowerList() {
        homeViewModel.getFollowerList()
    }

    private fun observeFollowerList() {
        homeViewModel.followerList.observe(viewLifecycleOwner) {
            followerAdapter.setFollowerList(it)
        }
    }

    private fun observeStateMessage() {
        homeViewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                State.SUCCESS -> return@observe
                State.NULL -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_home_null)
                )
                State.FAIL -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_home_fail)
                )
                State.SERVER_ERROR -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
                else -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_unknown_error)
                )
            }
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
