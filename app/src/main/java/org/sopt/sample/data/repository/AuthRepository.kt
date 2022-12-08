package org.sopt.sample.data.repository

import org.sopt.sample.data.dto.request.RequestLoginDto
import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.data.dto.response.ResponseLoginDto
import org.sopt.sample.data.dto.response.ResponseSignupDto

interface AuthRepository {
    suspend fun login(requestLoginDto: RequestLoginDto): Result<ResponseLoginDto>

    suspend fun signup(requestSignupDto: RequestSignupDto): Result<ResponseSignupDto>
}
