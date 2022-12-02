package org.sopt.sample.data.repository

import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto
import retrofit2.Response

interface FollowerRepository {
    suspend fun getFollowerList(page: Int): Result<Response<ResponseGetFollowerListDto>>
}
