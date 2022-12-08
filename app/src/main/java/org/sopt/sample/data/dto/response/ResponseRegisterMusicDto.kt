package org.sopt.sample.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseRegisterMusicDto(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val data: ResponseGetMusicListDto.Music
)
