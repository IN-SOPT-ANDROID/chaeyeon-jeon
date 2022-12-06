package org.sopt.sample.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    val email: String,
    val password: String,
    val name: String
)
