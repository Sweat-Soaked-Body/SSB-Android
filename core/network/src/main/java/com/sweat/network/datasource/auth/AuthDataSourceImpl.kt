package com.sweat.network.datasource.auth

import com.sweat.network.api.AuthApi
import com.sweat.network.dto.auth.request.LoginRequest
import com.sweat.network.dto.auth.request.SignUpRequest
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val service: AuthApi
): AuthDataSource {
    override fun authLogin(body: LoginRequest): Flow<Unit> =
        performApiRequest { service.login(body = body) }

    override fun authSignUp(body: SignUpRequest): Flow<Unit> =
        performApiRequest { service.signUp(body = body) }
}