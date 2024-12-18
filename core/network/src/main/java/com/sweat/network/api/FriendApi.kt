package com.sweat.network.api

import com.sweat.network.dto.auth.request.LoginRequest
import com.sweat.network.dto.friend.UserConnection
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FriendApi {
    @GET("/friend")
    suspend fun friendCheck(
        @Body body: UserConnection
    ): List<String>

    @POST("/friend")
    suspend fun friendAdd(
        @Body body: LoginRequest
    )

    @DELETE("/friend")
    suspend fun friendDelete(
        @Query("id") id: String,
    )
}