package com.sweat.network.dto.profile

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val message: String,
    val user: String,
    val timestamp: String
)
