package com.sweat.network.datasource.main

import com.sweat.network.api.MainApi
import com.sweat.network.dto.main.ExerciseRoutineResponse
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val service: MainApi
): MainDataSource {
    override suspend fun exerciseRoutineCheck(date: String): Flow<List<ExerciseRoutineResponse>> =
        performApiRequest { service.exerciseRoutineCheck(date = date) }
}