package com.sweat.network.mapper.main

import com.sweat.model.entity.main.FoodEntity
import com.sweat.model.entity.main.FoodRoutineResponseEntity
import com.sweat.network.dto.main.Food
import com.sweat.network.dto.main.FoodRoutineResponse

fun FoodRoutineResponse.toEntity(): FoodRoutineResponseEntity =
    FoodRoutineResponseEntity(
        id = this.id,
        food = this.food.map { it.toEntity() },
        date = this.date,
        serviceUser = this.serviceUser,
        type = this.type
    )

fun Food.toEntity(): FoodEntity {
    return FoodEntity(
        id = this.id,
        name = this.name,
        weight = this.weight,
        calories = this.calories,
        image = this.image,
        serviceUser = this.serviceUser,
        diet = this.diet
    )
}