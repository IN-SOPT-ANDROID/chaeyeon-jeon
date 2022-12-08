package org.sopt.sample.presentation.main.user

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.util.binding.BindingFragment

class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
            binding.imgSample.load(it[0])
            binding.imgSample2.load(it[1])
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

        binding.btnImage.setOnClickListener {
            launcher.launch(PickVisualMediaRequest())
        }
    }

    companion object {
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}
