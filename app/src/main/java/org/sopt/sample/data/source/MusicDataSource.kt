package org.sopt.sample.data.source

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.sample.api.MusicService
import org.sopt.sample.data.dto.response.ResponseMusicDto
import org.sopt.sample.data.dto.response.wrapper.MusicBaseResponse
import javax.inject.Inject

class MusicDataSource @Inject constructor(
    private val musicService: MusicService
) {
    suspend fun getMusicList(): MusicBaseResponse<List<ResponseMusicDto>> =
        musicService.getMusicList()

    suspend fun registerMusic(
        image: MultipartBody.Part,
        requestBody: RequestBody
    ): MusicBaseResponse<ResponseMusicDto> =
        musicService.registerMusic(image, requestBody)
}
