package org.sopt.sample.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetFollowerListDTO(
    @SerialName("page")
    val page: Long,
    @SerialName("per_page")
    val perPage: Long,
    @SerialName("total")
    val total: Long,
    @SerialName("total_pages")
    val totalPages: Long,
    @SerialName("data")
    val data: List<Follower>,
    @SerialName("support")
    val support: Support
) {
    @Serializable
    data class Follower(
        @SerialName("id")
        val id: Long,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        @SerialName("avatar")
        val avatar: String
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String
    )
}
