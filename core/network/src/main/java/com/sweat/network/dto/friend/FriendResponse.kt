package com.sweat.network.dto.friend

import com.squareup.moshi.JsonClass
import com.sweat.model.friend.FriendModel

@JsonClass(generateAdapter = true)
data class FriendResponse(
    val id: Int,
    val friend: String,
)

fun FriendResponse.toModel() = FriendModel(id, friend)