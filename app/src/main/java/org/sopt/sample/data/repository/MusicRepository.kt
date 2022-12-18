package org.sopt.sample.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.dto.response.ResponseMusicDto
import org.sopt.sample.data.dto.response.wrapper.MusicBaseResponse

interface MusicRepository {
    suspend fun getMusicList(): Result<MusicBaseResponse<List<ResponseMusicDto>>>

    suspend fun registerMusic(
        image: MultipartBody.Part,
        requestBody: RequestBody
    ): Result<MusicBaseResponse<ResponseMusicDto>>
}
