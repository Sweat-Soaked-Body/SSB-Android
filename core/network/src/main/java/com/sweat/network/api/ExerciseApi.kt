package com.sweat.network.api

import com.sweat.network.dto.exercise.ExerciseAddRequest
import com.sweat.network.dto.exercise.ExerciseLikeRequest
import com.sweat.network.dto.exercise.ExerciseListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExerciseApi {
    @GET("/exercise")
    suspend fun exerciseList(): List<ExerciseListResponse>

    @POST("/exercise/like")
    suspend fun updateLike(
        @Body body: ExerciseLikeRequest
    ): ExerciseLikeRequest

    @POST("/exercise")
    suspend fun addExercise(
        @Body body: ExerciseAddRequest
    ): ExerciseAddRequest
}