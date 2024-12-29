package com.sweat.data.repository.exercise

import com.sweat.model.entity.exercise.ExerciseListResponseEntity
import com.sweat.network.datasource.exercise.ExerciseDataSource
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
}
