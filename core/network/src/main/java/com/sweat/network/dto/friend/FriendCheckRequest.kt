package com.sweat.network.dto.friend

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendCheckRequest(
    @Json(name = "id") val id: Int,
    @Json(name = "from_user") val fromUser: Int,
    @Json(name = "to_user") val toUser: Int
)
