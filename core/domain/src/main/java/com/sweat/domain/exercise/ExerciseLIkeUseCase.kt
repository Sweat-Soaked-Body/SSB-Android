package com.sweat.domain.exercise

import com.sweat.data.repository.exercise.ExerciseRepository
import com.sweat.model.param.exercise.ExerciseLikeRequestParam
import javax.inject.Inject

class ExerciseLikeRequestUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(body: ExerciseLikeRequestParam) = runCatching {
        repository.updateLike(body = body)
    }
}
