package org.sopt.sample.data.source

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.api.MusicService
import org.sopt.sample.data.dto.response.ResponseGetMusicListDto
import org.sopt.sample.data.dto.response.ResponseRegisterMusicDto
import javax.inject.Inject

class MusicDataSource @Inject constructor(
    private val musicService: MusicService
) {
    suspend fun getMusicList(): ResponseGetMusicListDto =
        musicService.getMusicList()

    suspend fun registerMusic(
        image: MultipartBody.Part,
        requestBody: RequestBody
    ): ResponseRegisterMusicDto =
        musicService.registerMusic(image, requestBody)
}
