package org.sopt.sample.data.repository

import okhttp3.MultipartBody
import org.sopt.sample.data.dto.request.RequestRegisterMusicDto
import org.sopt.sample.data.dto.response.ResponseGetMusicListDto
import org.sopt.sample.data.dto.response.ResponseRegisterMusicDto

interface MusicRepository {
    suspend fun getMusicList(): Result<ResponseGetMusicListDto>

    suspend fun registerMusic(
        image: MultipartBody.Part,
        requestRegisterMusicDto: RequestRegisterMusicDto
    ): Result<ResponseRegisterMusicDto>
}
