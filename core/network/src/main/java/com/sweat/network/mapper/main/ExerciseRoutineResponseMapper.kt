package com.sweat.network.mapper.main

import com.sweat.model.entity.main.ExerciseRoutineResponseEntity
import com.sweat.model.entity.main.ExerciseSetEntity
import com.sweat.network.dto.main.ExerciseRoutineResponse
import com.sweat.network.dto.main.ExerciseSet

fun ExerciseRoutineResponse.toEntity(): ExerciseRoutineResponseEntity =
    ExerciseRoutineResponseEntity(
        id = this.id,
        exercise = this.exercise,
        sets = this.sets.map { it.toEntity() }
    )

fun ExerciseSet.toEntity(): ExerciseSetEntity {
    return ExerciseSetEntity(
        set = this.set,
        routine = this.routine,
        weight = this.weight,
        count = this.count,
        min = this.min,
        sec = this.sec,
        status = this.status
    )
}