package org.sopt.sample.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    val email: String,
    val password: String
)
