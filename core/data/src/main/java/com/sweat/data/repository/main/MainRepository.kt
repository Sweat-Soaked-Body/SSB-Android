package com.sweat.data.repository.main

import com.sweat.model.entity.main.ExerciseRoutineResponseEntity
import com.sweat.model.entity.main.FoodRoutineResponseEntity
import com.sweat.model.param.main.ExerciseSetRequestParam
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun exerciseRoutineCheck(date:String): Flow<List<ExerciseRoutineResponseEntity>>

    suspend fun foodRoutineCheck(date: String): Flow<List<FoodRoutineResponseEntity>>

    suspend fun exerciseSetAdd(body: ExerciseSetRequestParam): Flow<Unit>

}