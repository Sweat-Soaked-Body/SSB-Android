package com.sweat.network.dto.main

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExerciseSetRequest(
    @Json(name = "routine") val routine: Int,
    @Json(name = "weight") val weight: Int?,
    @Json(name = "count") val count: Int?,
    @Json(name = "min") val min: Int?,
    @Json(name = "sec") val sec: Int?
)