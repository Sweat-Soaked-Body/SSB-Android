package com.sweat.network.datasource

import com.sweat.network.dto.auth.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun authLogin(body: LoginRequest): Flow<Unit>
}