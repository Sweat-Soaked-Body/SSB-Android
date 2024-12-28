package com.sweat.model.entity.main

data class ExerciseRoutineResponseEntity(
    val id: Int,
    val exercise: Int,
    val sets: List<ExerciseSetEntity>
)

data class ExerciseSetEntity(
    val set: Int,
    val routine: Int,
    val weight: Int,
    val count: Int?,
    val min: Int?,
    val sec: Int?,
    val status: String // "unfinished" or "finished"
)