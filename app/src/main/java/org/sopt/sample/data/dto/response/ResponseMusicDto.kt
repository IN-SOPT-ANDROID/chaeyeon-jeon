package org.sopt.sample.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMusicDto(
    val id: Int,
    val image: String,
    val title: String,
    val singer: String
)
