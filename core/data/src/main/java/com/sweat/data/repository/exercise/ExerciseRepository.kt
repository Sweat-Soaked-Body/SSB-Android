package com.sweat.data.repository.exercise

import com.sweat.model.entity.exercise.ExerciseListResponseEntity
import com.sweat.model.param.exercise.ExerciseAddRequestParam
import com.sweat.model.param.exercise.ExerciseLikeRequestParam
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun exerciseList(id: Int): Flow<List<ExerciseListResponseEntity>>

    fun updateLike(body: ExerciseLikeRequestParam): Flow<Unit>

    fun addExercise(body: ExerciseAddRequestParam): Flow<Unit>
}