package org.sopt.sample.presentation.main.music

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.data.local.UiState
import org.sopt.sample.databinding.FragmentMusicBinding
import org.sopt.sample.util.binding.BindingFragment
import org.sopt.sample.util.extension.showSnackbar

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(R.layout.fragment_music) {
    private val viewModel by viewModels<MusicViewModel>()
    private val musicAdapter by lazy { MusicAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initMusicRecyclerView()
        getMusicList()
        observeStateMessage()
    }

    private fun initMusicRecyclerView() {
        binding.rvMusic.adapter = musicAdapter
        binding.rvMusic.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getMusicList() {
        viewModel.getMusicList()
    }

    private fun observeStateMessage() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                UiState.SUCCESS -> viewModel.musicList.value?.let { it ->
                    musicAdapter.setMusicList(it)
                }
                UiState.NULL -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_music_null)
                )
                UiState.SERVER_ERROR -> requireContext().showSnackbar(
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
        fun newInstance(): MusicFragment {
            return MusicFragment()
        }
    }
}
