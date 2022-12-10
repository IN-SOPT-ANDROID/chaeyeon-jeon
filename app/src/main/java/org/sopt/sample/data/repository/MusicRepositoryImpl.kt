package org.sopt.sample.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.data.dto.response.ResponseGetMusicListDto
import org.sopt.sample.data.dto.response.ResponseRegisterMusicDto
import org.sopt.sample.data.source.MusicDataSource
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {
    override suspend fun getMusicList(): Result<ResponseGetMusicListDto> =
        kotlin.runCatching { musicDataSource.getMusicList() }

    override suspend fun registerMusic(
        image: MultipartBody.Part,
        requestBody: RequestBody
    ): Result<ResponseRegisterMusicDto> =
        kotlin.runCatching { musicDataSource.registerMusic(image, requestBody) }
}
