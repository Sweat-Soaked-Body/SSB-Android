package com.sweat.network.dto.friend

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendResponse(
    val friend: List<FriendDto>,
)

@JsonClass(generateAdapter = true)
data class FriendDto(
    val id: Int,
    val friend: String,
)