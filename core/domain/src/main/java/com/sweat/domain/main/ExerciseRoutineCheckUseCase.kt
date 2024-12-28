package com.sweat.domain.main

import com.sweat.data.repository.main.MainRepository
import javax.inject.Inject

class ExerciseRoutineCheckUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    suspend operator fun invoke(date: String) = runCatching {
        mainRepository.exerciseRoutineCheck(date = date)
    }
}