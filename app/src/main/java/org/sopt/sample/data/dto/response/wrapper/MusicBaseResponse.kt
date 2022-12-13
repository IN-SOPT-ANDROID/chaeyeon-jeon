package org.sopt.sample.data.dto.response.wrapper

import kotlinx.serialization.Serializable

@Serializable
data class MusicBaseResponse<T>(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val data: T
)
