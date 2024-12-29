package com.sweat.network.api

import com.sweat.network.dto.exercise.ExerciseListResponse
import retrofit2.http.GET

interface ExerciseApi {
    @GET("/exercise")
    suspend fun exerciseList(): List<ExerciseListResponse>
}