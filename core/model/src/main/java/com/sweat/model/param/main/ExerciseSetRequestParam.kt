package com.sweat.model.param.main

data class ExerciseSetRequestParam(
    val routine: Int,
    val weight: Int?,
    val count: Int?,
    val min: Int?,
    val sec: Int?
)