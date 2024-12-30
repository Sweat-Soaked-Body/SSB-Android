package com.sweat.network.mapper.exercise

import com.sweat.model.entity.exercise.ExerciseListResponseEntity
import com.sweat.network.dto.exercise.ExerciseListResponse

fun ExerciseListResponse.toModel(): ExerciseListResponseEntity =
    ExerciseListResponseEntity(
        id = this.id,
        name = this.name,
        category = this.category,
        serviceUser = this.serviceUser,
        like = this.like
    )