package com.sweat.network.datasource.friend

import com.sweat.network.dto.friend.AddFriendRequest
import com.sweat.network.dto.friend.FriendResponse
import kotlinx.coroutines.flow.Flow

interface FriendDataSource {
    fun friendCheck(): Flow<List<FriendResponse>>
    fun friendAdd(body: AddFriendRequest): Flow<Unit>
    fun friendDelete(id: String): Flow<Unit>
}