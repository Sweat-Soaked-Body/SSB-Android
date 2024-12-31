package com.sweat.network.datasource.main

import com.sweat.network.dto.main.ExerciseRoutineResponse
import com.sweat.network.dto.main.ExerciseSetRequest
import com.sweat.network.dto.main.FoodRoutineResponse
import kotlinx.coroutines.flow.Flow

interface MainDataSource {
    suspend fun exerciseRoutineCheck(date: String): Flow<List<ExerciseRoutineResponse>>

    suspend fun foodRoutineCheck(date: String): Flow<List<FoodRoutineResponse>>

    suspend fun exerciseSetAdd(body: ExerciseSetRequest): Flow<Unit>

    suspend fun deleteExerciseSet(setId: Int): Flow<Unit>

    suspend fun deleteExerciseRoutine(routineId: Int): Flow<Unit>
}