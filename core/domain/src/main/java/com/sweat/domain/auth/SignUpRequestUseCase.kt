package com.sweat.domain.auth

import com.sweat.data.repository.auth.AuthRepository
import com.sweat.model.param.auth.SignUpRequestParam
import javax.inject.Inject

class SignUpRequestUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(body: SignUpRequestParam) = runCatching {
        repository.signUp(body = body)
    }
}