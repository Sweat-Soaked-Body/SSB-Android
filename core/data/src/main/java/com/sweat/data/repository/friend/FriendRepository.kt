package com.sweat.data.repository.friend

import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    fun friendCheck(): Flow<List<String>>
    fun friendAdd(toUser: Int): Flow<Unit>
    fun friendDelete(id: String): Flow<Unit>
}
