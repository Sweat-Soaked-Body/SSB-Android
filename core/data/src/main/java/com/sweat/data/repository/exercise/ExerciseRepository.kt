package com.sweat.data.repository.exercise

import com.sweat.model.entity.exercise.ExerciseListResponseEntity
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun exerciseList(id: Int): Flow<List<ExerciseListResponseEntity>>
}