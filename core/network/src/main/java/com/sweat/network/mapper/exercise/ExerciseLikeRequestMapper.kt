package com.sweat.network.mapper.exercise

import com.sweat.model.param.exercise.ExerciseLikeRequestParam
import com.sweat.network.dto.exercise.ExerciseLikeRequest

fun ExerciseLikeRequestParam.toDto(): ExerciseLikeRequest =
    ExerciseLikeRequest(
        exercise = this.exercise
    )