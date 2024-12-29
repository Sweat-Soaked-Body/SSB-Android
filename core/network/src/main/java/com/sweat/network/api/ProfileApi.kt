package com.sweat.network.api

import com.sweat.network.dto.profile.ProfileBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApi {
    @GET("/profile")
    fun getProfile(@Body body: ProfileBody): Unit

    @POST("/profile")
    fun updateProfile(): Unit
}