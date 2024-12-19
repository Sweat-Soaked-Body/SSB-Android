package com.sweat.network.api

import com.sweat.network.dto.auth.request.LoginRequest
import com.sweat.network.dto.auth.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/signin")
    suspend fun login(
        @Body body: LoginRequest
    )

    @POST("/auth/signup")
    suspend fun signUp(
        @Body body: SignUpRequest
    )
}