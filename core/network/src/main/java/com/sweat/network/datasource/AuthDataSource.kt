package com.sweat.network.datasource

import com.sweat.network.dto.auth.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun authLogin(body: LoginRequest): Flow<Unit>
}