package com.sweat.domain.main

import com.sweat.data.repository.main.MainRepository
import com.sweat.model.param.main.ExerciseSetRequestParam
import javax.inject.Inject

class ExerciseSetAddUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(body: ExerciseSetRequestParam) = runCatching {
        mainRepository.exerciseSetAdd(body = body)
    }
}