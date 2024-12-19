package com.sweat.network.dto.auth.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "name") val name: String,
    @Json(name = "sex") val sex: String,
    @Json(name = "age") val age: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "height") val height: Int
)
