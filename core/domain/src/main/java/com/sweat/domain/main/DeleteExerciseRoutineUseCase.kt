package com.sweat.domain.main

import com.sweat.data.repository.main.MainRepository
import javax.inject.Inject

class DeleteExerciseRoutineUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(routineId: Int) = runCatching {
        mainRepository.deleteExerciseRoutine(routineId = routineId)
    }
}