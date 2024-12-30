package com.sweat.model.entity.exercise

data class ExerciseListResponseEntity(
    val id: Int,
    val name: String,
    val category: Int,
    val serviceUser: Int,
    val like: Boolean
)
