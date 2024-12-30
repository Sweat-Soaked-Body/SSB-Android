package com.sweat.network.datasource.main

import com.sweat.network.api.MainApi
import com.sweat.network.dto.main.ExerciseRoutineResponse
import com.sweat.network.dto.main.ExerciseSetRequest
import com.sweat.network.dto.main.FoodRoutineResponse
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val service: MainApi
): MainDataSource {
    override suspend fun exerciseRoutineCheck(date: String): Flow<List<ExerciseRoutineResponse>> =
        performApiRequest { service.exerciseRoutineCheck(date = date) }

    override suspend fun foodRoutineCheck(date: String): Flow<List<FoodRoutineResponse>> =
        performApiRequest { service.foodRoutineCheck(date = date) }

    override suspend fun exerciseSetAdd(body: ExerciseSetRequest): Flow<Unit> =
        performApiRequest { service.exerciseSetAdd(body = body) }


    override suspend fun deleteExerciseSet(setId: Int): Flow<Unit> =
        performApiRequest { service.deleteExerciseSet(setId = setId) }

    override suspend fun deleteExerciseRoutine(routineId: Int): Flow<Unit> =
        performApiRequest { service.deleteExerciseRoutine(routineId = routineId) }
}