package com.sweat.domain.main

import com.sweat.data.repository.main.MainRepository
import javax.inject.Inject

class FoodRoutineCheckUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    suspend operator fun invoke(date: String) = runCatching {
        mainRepository.foodRoutineCheck(date = date)
    }
}