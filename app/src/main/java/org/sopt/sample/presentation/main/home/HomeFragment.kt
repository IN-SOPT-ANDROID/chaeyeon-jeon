package org.sopt.sample.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.presentation.main.home.FollowerAdapter.Companion.VIEW_TYPE_HEADER
import org.sopt.sample.presentation.main.home.FollowerAdapter.Companion.VIEW_TYPE_ITEM
import org.sopt.sample.util.UiState
import org.sopt.sample.util.binding.BindingFragment
import org.sopt.sample.util.extension.showSnackbar

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private val followerAdapter by lazy { FollowerAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initFollowerRecyclerView()
        observeStateMessage()
    }

    private fun initFollowerRecyclerView() {
        binding.rvFollower.adapter = followerAdapter

        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (followerAdapter.getItemViewType(position)) {
                    VIEW_TYPE_HEADER -> 2
                    VIEW_TYPE_ITEM -> 1
                    else -> throw ClassCastException(
                        "Unknown View Type : ${followerAdapter.getItemViewType(position)}"
                    )
                }
            }
        }
        binding.rvFollower.layoutManager = layoutManager
    }

    private fun observeStateMessage() {
        viewModel.stateMessage.observe(viewLifecycleOwner) { it ->
            when (it) {
                is UiState.Success -> viewModel.followerList.value?.let {
                    followerAdapter.submitList(it)
                }
                is UiState.Failure -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_home_null)
                )
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
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
