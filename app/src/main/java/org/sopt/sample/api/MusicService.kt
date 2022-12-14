package org.sopt.sample.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.dto.response.ResponseMusicDto
import org.sopt.sample.data.dto.response.wrapper.MusicBaseResponse
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MusicService {
    // 음악 리스트 조회 API
    @GET("music/list")
    suspend fun getMusicList(): MusicBaseResponse<List<ResponseMusicDto>>

    // 음악 생성 API
    @Multipart
    @POST("music")
    suspend fun registerMusic(
        @Part image: MultipartBody.Part?,
        @Part("request") request: RequestBody
    ): MusicBaseResponse<ResponseMusicDto>
}
