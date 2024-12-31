package com.sweat.network.api

import com.sweat.network.dto.main.ExerciseRoutineResponse
import com.sweat.network.dto.main.ExerciseSetRequest
import com.sweat.network.dto.main.FoodRoutineResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("/routine")
    suspend fun exerciseRoutineCheck(
        @Query("date") date: String
    ): List<ExerciseRoutineResponse>

    @GET("/diet/search")
    suspend fun foodRoutineCheck(
        @Query("date") date: String
    ): List<FoodRoutineResponse>

    @POST("/routine/set")
    suspend fun exerciseSetAdd(
        @Body body: ExerciseSetRequest
    )

    @DELETE("/routine/set/{set_id}")
    suspend fun deleteExerciseSet(
        @Path("set_id") setId: Int
    )

    @DELETE("/routine/{routine_id}")
    suspend fun deleteExerciseRoutine(
        @Path("routine_id") routineId: Int
    )
}