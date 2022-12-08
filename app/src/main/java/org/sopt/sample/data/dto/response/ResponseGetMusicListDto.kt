package org.sopt.sample.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMusicListDto(
    val statusCode: Int,
    val success: Boolean,
    val message: String,
    val data: List<Music>
) {
    @Serializable
    data class Music(
        val id: Int,
        val image: String,
        val title: String,
        val singer: String
    )
}
