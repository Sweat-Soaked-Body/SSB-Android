package com.sweat.data.repository.main

import com.sweat.model.entity.main.ExerciseRoutineResponseEntity
import com.sweat.network.datasource.main.MainDataSource
import com.sweat.network.dto.main.ExerciseRoutineResponse
import com.sweat.network.mapper.exerciseRoutine.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
): MainRepository {
    override suspend fun exerciseRoutineCheck(date: String): Flow<List<ExerciseRoutineResponseEntity>> {
         return mainDataSource.exerciseRoutineCheck(date = date).map { list -> list.map { it.toEntity() } }
    }
}