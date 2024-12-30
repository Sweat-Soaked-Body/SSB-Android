package com.sweat.model.entity.main

data class FoodRoutineResponseEntity(
    val id: Int,
    val food: List<FoodEntity>,
    val date: String, // %Y-%m-%d 형식
    val serviceUser: Int,
    val type: String,
    val image: String
)

data class FoodEntity(
    val id: Int,
    val name: String,
    val weight: Int,
    val calories: Int,
    val serviceUser: Int,
    val diet: Int
)