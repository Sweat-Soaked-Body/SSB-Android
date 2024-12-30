package com.sweat.network.api

import com.sweat.network.dto.profile.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApi {
    @GET("/profile")
    suspend fun getProfile(): ProfileResponse

    @POST("/profile")
    suspend fun updateProfile(): Unit
}