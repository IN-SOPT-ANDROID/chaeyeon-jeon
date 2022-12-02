package org.sopt.sample.data.source

import org.sopt.sample.api.FollowerService
import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto
import retrofit2.Response
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val followerService: FollowerService
) {
    suspend fun getFollowerList(page: Int): Response<ResponseGetFollowerListDto> =
        followerService.getFollowerList(page)
}
