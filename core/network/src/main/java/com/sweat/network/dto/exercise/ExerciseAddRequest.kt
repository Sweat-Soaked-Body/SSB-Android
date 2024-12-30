package com.sweat.network.dto.exercise

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExerciseAddRequest (
    @Json(name = "name") val name: String,
    @Json(name = "category") val category: Int
)