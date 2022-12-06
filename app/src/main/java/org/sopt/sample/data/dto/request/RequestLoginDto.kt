package org.sopt.sample.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    val email: String,
    val password: String
)
