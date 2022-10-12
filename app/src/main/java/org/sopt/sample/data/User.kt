package org.sopt.sample.data

import androidx.annotation.DrawableRes
import java.io.Serializable

data class User(
    @DrawableRes val img: Int,
    val id: String,
    val pwd: String,
    val mbti: String,
): Serializable {}
