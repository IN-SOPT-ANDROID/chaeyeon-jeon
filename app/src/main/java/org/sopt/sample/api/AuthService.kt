package org.sopt.sample.api

import org.sopt.sample.data.remote.RequestLoginDto
import org.sopt.sample.data.remote.RequestSignupDto
import org.sopt.sample.data.remote.ResponseLoginDto
import org.sopt.sample.data.remote.ResponseSignupDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 로그인 API
    @POST("api/user/signin")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>

    // 회원가입 API
    @POST("api/user/signup")
    fun signup(
        @Body request: RequestSignupDto
    ): Call<ResponseSignupDto>
}
