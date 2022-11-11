package org.sopt.sample.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 로그인 API
    @POST("api/user/signin")
    fun login(
        @Body request: RequestLoginDTO
    ): Call<ResponseLoginDTO>

    // 회원가입 API
    @POST("api/user/signup")
    fun signup(
        @Body request: RequestSignupDTO
    ): Call<ResponseSignupDTO>
}