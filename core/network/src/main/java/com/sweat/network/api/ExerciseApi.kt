package com.sweat.network.api

import com.sweat.network.dto.exercise.ExerciseLikeRequest
import com.sweat.network.dto.exercise.ExerciseListResponse
import com.sweat.network.dto.friend.AddFriendRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExerciseApi {
    @GET("/exercise")
    suspend fun exerciseList(): List<ExerciseListResponse>

    @POST("/exercise/like")
    suspend fun updateLike(
        @Body body: ExerciseLikeRequest
    )
}