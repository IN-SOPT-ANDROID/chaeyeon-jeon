package org.sopt.sample.api

import okhttp3.MultipartBody
import org.sopt.sample.data.dto.request.RequestRegisterMusicDto
import org.sopt.sample.data.dto.response.ResponseGetMusicListDto
import org.sopt.sample.data.dto.response.ResponseRegisterMusicDto
import retrofit2.http.* // ktlint-disable no-wildcard-imports

interface MusicService {
    // 음악 리스트 조회 API
    @GET("music/list")
    suspend fun getMusicList(): ResponseGetMusicListDto

    // 음악 생성 API
    @Multipart
    @POST("music")
    suspend fun registerMusic(
        @Part image: MultipartBody.Part?,
        @Body requestRegisterMusic: RequestRegisterMusicDto
    ): ResponseRegisterMusicDto
}
