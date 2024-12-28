package com.sweat.network.dto.main

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class  ExerciseRoutineResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "exercise") val exercise: Int,
    @Json(name = "sets") val sets: List<ExerciseSet>
)

@JsonClass(generateAdapter = true)
data class ExerciseSet(
    @Json(name = "set") val set: Int,
    @Json(name = "routine") val routine: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "count") val count: Int?,
    @Json(name = "min") val min: Int?,
    @Json(name = "sec") val sec: Int?,
    @Json(name = "status") val status: String // "unfinished" or "finished"
)