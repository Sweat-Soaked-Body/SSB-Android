package com.sweat.data.repository.main

import com.sweat.model.entity.main.ExerciseRoutineResponseEntity
import com.sweat.model.entity.main.FoodRoutineResponseEntity
import com.sweat.model.param.main.ExerciseSetRequestParam
import com.sweat.network.datasource.main.MainDataSource
import com.sweat.network.dto.main.ExerciseSetRequest
import com.sweat.network.mapper.main.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
): MainRepository {
    override suspend fun exerciseRoutineCheck(date: String): Flow<List<ExerciseRoutineResponseEntity>> {
         return mainDataSource.exerciseRoutineCheck(date = date).map { list -> list.map { it.toEntity() } }
    }

    override suspend fun foodRoutineCheck(date: String): Flow<List<FoodRoutineResponseEntity>> {
        return mainDataSource.foodRoutineCheck(date = date).map { list -> list.map { it.toEntity() } }
    }

    override suspend fun exerciseSetAdd(body: ExerciseSetRequestParam): Flow<Unit> {
        val request = ExerciseSetRequest(
            routine = body.routine,
            weight = body.weight,
            count = body.count,
            min = body.min,
            sec = body.sec
        )
        return mainDataSource.exerciseSetAdd(body = request)
    }
}