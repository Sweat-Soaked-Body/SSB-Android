package com.sweat.data.repository.auth

import com.sweat.model.param.auth.LoginRequestParam
import com.sweat.model.param.auth.SignUpRequestParam
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(body: LoginRequestParam): Flow<Unit>
    fun signUp(body: SignUpRequestParam) : Flow<Unit>
}