package org.sopt.sample.api

import org.sopt.sample.data.remote.RequestLoginDTO
import org.sopt.sample.data.remote.RequestSignupDTO
import org.sopt.sample.data.remote.ResponseLoginDTO
import org.sopt.sample.data.remote.ResponseSignupDTO
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