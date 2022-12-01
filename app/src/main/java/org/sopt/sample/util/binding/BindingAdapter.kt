package org.sopt.sample.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.sopt.sample.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun ImageView.setImage(imgUrl: String?) {
        this.let {
            Glide.with(context)
                .load(imgUrl)
                .circleCrop()
                .error(R.drawable.img_photo_error)
                .into(this)
        }
    }
}
