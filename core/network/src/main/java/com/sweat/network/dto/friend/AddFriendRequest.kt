package com.sweat.network.dto.friend

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddFriendRequest(
     val name: String
)