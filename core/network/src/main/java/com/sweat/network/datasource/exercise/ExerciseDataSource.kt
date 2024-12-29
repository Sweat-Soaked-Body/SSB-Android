package com.sweat.network.datasource.exercise

import com.sweat.network.dto.exercise.ExerciseListResponse
import kotlinx.coroutines.flow.Flow

interface ExerciseDataSource {
    fun exerciseList(): Flow<List<ExerciseListResponse>>
}