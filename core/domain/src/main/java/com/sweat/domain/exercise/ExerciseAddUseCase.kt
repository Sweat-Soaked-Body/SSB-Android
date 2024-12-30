package com.sweat.domain.exercise

import com.sweat.data.repository.exercise.ExerciseRepository
import com.sweat.model.param.exercise.ExerciseAddRequestParam
import javax.inject.Inject

class ExerciseAddUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(body: ExerciseAddRequestParam) = runCatching {
        repository.addExercise(body = body)
    }
}