package org.sopt.sample.data.repository

import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto
import org.sopt.sample.data.source.FollowerDataSource
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource
) : FollowerRepository {
    override suspend fun getFollowerList(page: Int): Result<ResponseGetFollowerListDto> =
        kotlin.runCatching { followerDataSource.getFollowerList(page) }
}
