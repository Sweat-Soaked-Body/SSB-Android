package com.sweat.network.datasource.auth

import com.sweat.network.dto.auth.request.LoginRequest
import com.sweat.network.dto.auth.request.SignUpRequest
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun authLogin(body: LoginRequest): Flow<Unit>
  
    fun authSignUp(body: SignUpRequest) : Flow<Unit>
}