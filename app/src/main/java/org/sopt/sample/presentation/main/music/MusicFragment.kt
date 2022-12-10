package org.sopt.sample.presentation.main.music

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentMusicBinding
import org.sopt.sample.util.binding.BindingFragment

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(R.layout.fragment_music) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): MusicFragment {
            return MusicFragment()
        }
    }
}
