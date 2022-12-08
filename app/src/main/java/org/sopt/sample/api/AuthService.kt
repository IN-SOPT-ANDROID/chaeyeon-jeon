package org.sopt.sample.api

import org.sopt.sample.data.dto.request.RequestLoginDto
import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.data.dto.response.ResponseLoginDto
import org.sopt.sample.data.dto.response.ResponseSignupDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 로그인 API
    @POST("api/user/signin")
    suspend fun login(
        @Body request: RequestLoginDto
    ): ResponseLoginDto

    // 회원가입 API
    @POST("api/user/signup")
    suspend fun signup(
        @Body request: RequestSignupDto
    ): ResponseSignupDto
}
