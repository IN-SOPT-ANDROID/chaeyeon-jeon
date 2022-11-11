package org.sopt.sample.api

import org.sopt.sample.data.remote.ResponseGetFollowerListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    // Reqres Get List Users API
    @GET("api/users")
    fun getFollowerList(
        @Query("page") page: Int
    ): Call<ResponseGetFollowerListDTO>
}