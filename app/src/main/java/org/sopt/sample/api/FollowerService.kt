package org.sopt.sample.api

import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    // Reqres Get List Users API
    @GET("api/users")
    suspend fun getFollowerList(
        @Query("page") page: Int
    ): ResponseGetFollowerListDto
}
