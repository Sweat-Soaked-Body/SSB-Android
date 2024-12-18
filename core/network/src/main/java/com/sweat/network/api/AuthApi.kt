package com.sweat.network.api

import com.sweat.network.dto.auth.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/signin")
    suspend fun login(
        @Body body: LoginRequest
    )
}