package org.sopt.sample.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestRegisterMusicDto(
    val singer: String,
    val title: String
)
