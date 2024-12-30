package com.sweat.network.datasource.exercise

import android.util.Log
import com.sweat.network.api.ExerciseApi
import com.sweat.network.dto.exercise.AddExerciseRequest
import com.sweat.network.dto.exercise.ExerciseLikeRequest
import com.sweat.network.dto.exercise.ExerciseListResponse
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseDataSourceImpl @Inject constructor(
    private val service: ExerciseApi
) : ExerciseDataSource {

    override fun exerciseList(): Flow<List<ExerciseListResponse>> =
        performApiRequest { service.exerciseList() }

    override fun updateLike(body: ExerciseLikeRequest): Flow<Unit> {
        return performApiRequest {
            service.updateLike(body = body)
        }
    }

}
