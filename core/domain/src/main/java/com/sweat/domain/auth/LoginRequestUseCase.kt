package com.sweat.domain.auth

import com.sweat.data.repository.auth.AuthRepository
import com.sweat.model.param.auth.LoginRequestParam
import java.util.concurrent.Flow
import javax.inject.Inject

class LoginRequestUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    operator fun invoke(body: LoginRequestParam) = runCatching {
        authRepository.login(body = body)
    }
}
