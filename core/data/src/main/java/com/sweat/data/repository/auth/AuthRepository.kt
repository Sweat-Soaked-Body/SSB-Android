package com.sweat.data.repository.auth

import com.sweat.model.param.auth.LoginRequestParam
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(body: LoginRequestParam): Flow<Unit>
}