package com.sweat.network.datasource.auth

import com.sweat.network.dto.friend.AddFriendRequest
import com.sweat.network.dto.friend.FriendCheckRequest
import com.sweat.network.dto.friend.FriendResponse
import kotlinx.coroutines.flow.Flow

interface FriendDataSource {
    fun friendCheck(body: FriendCheckRequest): Flow<FriendResponse>
    fun friendAdd(body: AddFriendRequest): Flow<Unit>
    fun friendDelete(id: String): Flow<Unit>
}