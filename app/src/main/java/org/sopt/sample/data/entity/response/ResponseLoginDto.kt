package org.sopt.sample.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    val status: Int,
    val message: String,
    val result: User
) {
    @Serializable
    data class User(
        val id: Long,
        val name: String,
        val profileImage: String?,
        val bio: String?,
        val email: String,
        val password: String
    )
}
