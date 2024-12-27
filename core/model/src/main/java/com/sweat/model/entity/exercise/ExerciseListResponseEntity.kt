package com.sweat.model.entity.exercise

data class ExerciseListResponseEntity(
    val id: Long,
    val name: String,
    val category: Int,
    val serviceUserId: Int,
    val isFavorite: Boolean
)
