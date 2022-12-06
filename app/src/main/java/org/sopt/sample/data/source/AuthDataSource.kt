package org.sopt.sample.data.source

import org.sopt.sample.api.AuthService
import org.sopt.sample.data.dto.request.RequestLoginDto
import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.data.dto.response.ResponseLoginDto
import org.sopt.sample.data.dto.response.ResponseSignupDto
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun login(requestLoginDto: RequestLoginDto): ResponseLoginDto =
        authService.login(requestLoginDto)

    suspend fun signup(requestSignupDto: RequestSignupDto): ResponseSignupDto =
        authService.signup(requestSignupDto)
}
