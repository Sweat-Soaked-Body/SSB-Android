package com.sweat.network.dto.friend

import com.squareup.moshi.Json

data class AddFriendRequest(
    @Json(name = "to_user") val toUser: Int
)