package com.sweat.data.repository.main

import com.sweat.model.entity.main.ExerciseRoutineResponseEntity
import com.sweat.network.dto.main.ExerciseRoutineResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun exerciseRoutineCheck(date:String): Flow<List<ExerciseRoutineResponseEntity>>

}