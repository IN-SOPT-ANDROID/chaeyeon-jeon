package org.sopt.sample.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignupDto(
    val status: Int,
    val message: String,
    @SerialName("newUser")
    val result: ResponseLoginDto.User
)
