package org.sopt.sample.presentation.main.gallery

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.data.local.UiState
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.presentation.main.gallery.GalleryViewModel.Companion.IMAGE_NULL_CODE
import org.sopt.sample.presentation.main.gallery.GalleryViewModel.Companion.INCORRECT_INPUT_CODE
import org.sopt.sample.util.ContentUriRequestBody
import org.sopt.sample.util.binding.BindingFragment
import org.sopt.sample.util.extension.hideKeyboard
import org.sopt.sample.util.extension.showSnackbar

@AndroidEntryPoint
class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()

    private val launcher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        if (it == null) return@registerForActivityResult
        viewModel.setCoverImage(ContentUriRequestBody(requireContext(), it))
        binding.imgCover.load(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        requestStoragePermission()
        imageBtnOnClick()
        observeStateMessage()
    }

    private fun initHideKeyboard() {
        binding.layout.setOnClickListener { requireContext().hideKeyboard(requireView()) }
    }

    private fun requestStoragePermission() {
        val permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun imageBtnOnClick() {
        binding.imgCover.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun observeStateMessage() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_register_music_success)
                )
                is UiState.Failure -> {
                    when (it.code) {
                        IMAGE_NULL_CODE -> requireContext().showSnackbar(
                            binding.root,
                            getString(R.string.msg_image_null)
                        )
                        INCORRECT_INPUT_CODE -> requireContext().showSnackbar(
                            binding.root,
                            getString(R.string.msg_incorrect)
                        )
                    }
                }
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }

    companion object {
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}
