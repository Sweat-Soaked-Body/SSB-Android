package com.sweat.network.api

import com.sweat.network.dto.main.ExerciseRoutineResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("/routine")
    suspend fun exerciseRoutineCheck(
        @Path("date") date: String
    ): List<ExerciseRoutineResponse>
}