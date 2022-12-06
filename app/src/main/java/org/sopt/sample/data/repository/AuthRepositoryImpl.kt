package org.sopt.sample.data.repository

import org.sopt.sample.data.dto.request.RequestLoginDto
import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.data.dto.response.ResponseLoginDto
import org.sopt.sample.data.dto.response.ResponseSignupDto
import org.sopt.sample.data.source.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun login(requestLoginDto: RequestLoginDto): Result<ResponseLoginDto> =
        kotlin.runCatching { authDataSource.login(requestLoginDto) }

    override suspend fun signup(requestSignupDto: RequestSignupDto): Result<ResponseSignupDto> =
        kotlin.runCatching { authDataSource.signup(requestSignupDto) }
}
