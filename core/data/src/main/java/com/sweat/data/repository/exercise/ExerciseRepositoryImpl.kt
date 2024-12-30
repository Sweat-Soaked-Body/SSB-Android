package com.sweat.data.repository.exercise

import com.sweat.model.entity.exercise.ExerciseListResponseEntity
import com.sweat.model.param.exercise.ExerciseAddRequestParam
import com.sweat.model.param.exercise.ExerciseLikeRequestParam
import com.sweat.network.datasource.exercise.ExerciseDataSource
import com.sweat.network.dto.exercise.ExerciseAddRequest
import com.sweat.network.dto.exercise.ExerciseLikeRequest
import com.sweat.network.mapper.exercise.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDataSource: ExerciseDataSource
) : ExerciseRepository {

    override fun exerciseList(id: Int): Flow<List<ExerciseListResponseEntity>> {
        return exerciseDataSource.exerciseList().map { responseList ->
            responseList.map { response ->
                ExerciseListResponseEntity(
                    id = response.id,
                    name = response.name,
                    category = response.category,
                    serviceUser = response.serviceUser,
                    like = response.like
                )
            }
        }
    }

    override fun updateLike(body: ExerciseLikeRequestParam): Flow<Unit> {
        return exerciseDataSource.updateLike(
            body = body.toDto()
        )
    }

    override fun addExercise(body: ExerciseAddRequestParam): Flow<Unit> {
        return exerciseDataSource.addExercise(
            body = body.toDto()
        )
    }
}
