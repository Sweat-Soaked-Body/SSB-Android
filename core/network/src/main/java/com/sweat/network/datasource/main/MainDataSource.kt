package com.sweat.network.datasource.main

import com.sweat.network.dto.main.ExerciseRoutineResponse
import kotlinx.coroutines.flow.Flow

interface MainDataSource {
    suspend fun exerciseRoutineCheck(date: String): Flow<List<ExerciseRoutineResponse>>
}