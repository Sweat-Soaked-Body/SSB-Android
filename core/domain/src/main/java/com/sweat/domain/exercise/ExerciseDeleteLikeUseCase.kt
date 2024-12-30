package com.sweat.domain.exercise

import com.sweat.data.repository.exercise.ExerciseRepository
import javax.inject.Inject

class ExerciseDeleteLikeUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(id: Int) = runCatching {
        repository.deleteLike(id = id)
    }
}
