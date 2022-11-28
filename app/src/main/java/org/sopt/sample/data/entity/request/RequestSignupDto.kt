package org.sopt.sample.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    val email: String,
    val password: String,
    val name: String
)
