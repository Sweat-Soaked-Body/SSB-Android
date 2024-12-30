package com.sweat.network.mapper.main

import com.sweat.model.entity.main.ExerciseSetEntity
import com.sweat.model.param.main.ExerciseSetRequestParam
import com.sweat.network.dto.main.ExerciseSetRequest

fun ExerciseSetRequestParam.toDto(): ExerciseSetRequest =
    ExerciseSetRequest(
        routine = this.routine,
        weight = this.weight,
        count = this.count,
        min = this.min,
        sec = this.sec
    )