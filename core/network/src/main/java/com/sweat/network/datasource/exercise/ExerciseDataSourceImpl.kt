package com.sweat.network.datasource.exercise

import com.sweat.network.api.ExerciseApi
import com.sweat.network.dto.exercise.ExerciseListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExerciseDataSourceImpl @Inject constructor(
    private val exerciseApi: ExerciseApi
) : ExerciseDataSource {
    override fun exerciseList(): Flow<List<ExerciseListResponse>> {
        return flow {
            val response = exerciseApi.exerciseList() // Retrofit API 호출
            emit(response) // List<ExerciseListResponse>를 Flow로 변환
        }
    }
}
