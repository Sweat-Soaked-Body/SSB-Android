package com.sweat.network.api

import com.sweat.network.dto.friend.AddFriendRequest
import com.sweat.network.dto.friend.FriendResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FriendApi {
    @GET("/friend")
    suspend fun friendCheck(): FriendResponse

    @POST("/friend")
    suspend fun friendAdd(
        @Body body: AddFriendRequest,
    )

    @DELETE("/friend/{id}")
    suspend fun friendDelete(
        @Path("id") id: String,
    )
}