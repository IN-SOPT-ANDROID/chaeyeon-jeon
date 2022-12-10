package org.sopt.sample.data.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

@Serializable
data class RequestRegisterMusicDto(
    val singer: String,
    val title: String
) {
    fun toJsonObject() = buildJsonObject {
        put("singer", singer)
        put("title", title)
    }.toString().toRequestBody("text/plain".toMediaType())
}
