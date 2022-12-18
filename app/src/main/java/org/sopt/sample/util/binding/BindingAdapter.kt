package org.sopt.sample.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.sopt.sample.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun ImageView.setCircleImage(imgUrl: String?) {
        this.let {
            Glide.with(context)
                .load(imgUrl)
                .circleCrop()
                .error(R.drawable.img_photo_error)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("setCoverImage")
    fun ImageView.setCoverImage(imgUrl: String?) {
        this.let {
            Glide.with(context)
                .load(imgUrl)
                .transform(CenterCrop(), RoundedCorners(14))
                .error(R.drawable.img_photo_error)
                .into(this)
        }
    }
}
