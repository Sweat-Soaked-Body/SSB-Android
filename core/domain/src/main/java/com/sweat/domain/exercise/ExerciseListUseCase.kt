package com.sweat.domain.exercise

import com.sweat.data.repository.exercise.ExerciseRepository
import com.sweat.model.entity.exercise.ExerciseListResponseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseListUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) {
    operator fun invoke(id: Int): Flow<List<ExerciseListResponseEntity>> {
        return exerciseRepository.exerciseList(id)
    }
}
