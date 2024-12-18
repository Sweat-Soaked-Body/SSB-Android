package com.sweat.network.datasource

import com.sweat.network.api.AuthApi
import com.sweat.network.dto.auth.request.LoginRequest
import com.sweat.network.util.SSBApiHandler
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val serviceAuth: AuthApi
): com.sweat.network.datasource.AuthDataSource {
    override suspend fun authLogin(body: LoginRequest): Flow<Unit> =
        performApiRequest { serviceAuth.login(body = body) }
}