package com.sweat.network.dto.exercise

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExerciseListResponse (
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "category") val category: Int,
    @Json(name = "service_user") val service_user: Int,
    @Json(name = "like") val like: Boolean
)