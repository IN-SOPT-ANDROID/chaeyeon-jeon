package org.sopt.sample.data.repository

import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto

interface FollowerRepository {
    suspend fun getFollowerList(page: Int): Result<ResponseGetFollowerListDto>
}
