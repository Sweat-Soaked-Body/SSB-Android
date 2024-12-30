package com.sweat.domain.main

import com.sweat.data.repository.main.MainRepository
import javax.inject.Inject

class DeleteExerciseSetUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(setId: Int) = runCatching {
        mainRepository.deleteExerciseSet(setId = setId)
    }
}