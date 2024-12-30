package com.sweat.data.repository.friend

import com.sweat.model.friend.FriendModel
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun friendCheck(): Flow<List<FriendModel>>
    fun friendAdd(name: String): Flow<Unit>
    fun friendDelete(id: String): Flow<Unit>
}
