package com.sweat.data.repository.friend

import com.sweat.network.dto.friend.FriendDto
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun friendCheck(): Flow<List<FriendDto>>
    fun friendAdd(name: String): Flow<Unit>
    fun friendDelete(id: String): Flow<Unit>
}
