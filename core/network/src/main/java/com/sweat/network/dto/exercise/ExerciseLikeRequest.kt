package com.sweat.network.dto.exercise

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExerciseLikeRequest(
    @Json(name = "exercise") val exercise: Int
)
