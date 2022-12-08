package org.sopt.sample.data.source

import okhttp3.MultipartBody
import org.sopt.sample.api.MusicService
import org.sopt.sample.data.dto.request.RequestRegisterMusicDto
import org.sopt.sample.data.dto.response.ResponseGetMusicListDto
import org.sopt.sample.data.dto.response.ResponseRegisterMusicDto
import javax.inject.Inject

class MusicDataSource @Inject constructor(
    private val musicService: MusicService
) {
    suspend fun getMusicList(): ResponseGetMusicListDto =
        musicService.getMusicList()

    suspend fun registerMusic(image: MultipartBody.Part, requestRegisterMusic: RequestRegisterMusicDto): ResponseRegisterMusicDto =
        musicService.registerMusic(image, requestRegisterMusic)
}
