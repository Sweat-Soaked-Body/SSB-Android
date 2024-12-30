package com.sweat.network.mapper.exercise

import com.sweat.model.param.exercise.ExerciseAddRequestParam
import com.sweat.network.dto.exercise.ExerciseAddRequest

fun ExerciseAddRequestParam.toDto(): ExerciseAddRequest =
    ExerciseAddRequest(
        name = this.name,
        category = this.category
    )