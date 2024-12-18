package com.sweat.data.repository.auth

import com.sweat.model.param.auth.LoginRequestParam
import com.sweat.network.datasource.AuthDataSource
import com.sweat.network.mapper.request.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthDataSource
): AuthRepository {
    override fun login(body: LoginRequestParam): Flow<Unit> {
        return remoteDataSource.authLogin(
            body = body.toDto()
        )
    }
}