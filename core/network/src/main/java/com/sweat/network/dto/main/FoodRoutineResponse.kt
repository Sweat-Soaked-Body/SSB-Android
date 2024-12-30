package com.sweat.network.dto.main

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodRoutineResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "food") val food: List<Food>,
    @Json(name = "date") val date: String,
    @Json(name = "service_user") val serviceUser: Int,
    @Json(name = "type") val type: String
)

@JsonClass(generateAdapter = true)
data class Food(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "weight") val weight: Int,
    @Json(name = "calories") val calories: Int,
    @Json(name = "image") val image: String,
    @Json(name = "service_user") val serviceUser: Int,
    @Json(name = "diet") val diet: Int
)