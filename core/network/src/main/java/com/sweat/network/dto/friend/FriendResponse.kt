package com.sweat.network.dto.friend

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendResponse(
    val friend: List<String>
)