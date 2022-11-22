package org.sopt.sample.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    val email: String,
    val password: String
)
