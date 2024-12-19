package com.sweat.data.repository.friend

import com.sweat.network.datasource.friend.FriendDataSource
import com.sweat.network.dto.friend.AddFriendRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val friendDataSource: FriendDataSource
) : FriendRepository {
    override fun friendCheck(): Flow<List<String>> {
        return friendDataSource.friendCheck().map { it.friend }
    }

    override fun friendAdd(toUser: Int): Flow<Unit> {
        return friendDataSource.friendAdd(body = AddFriendRequest(toUser = toUser))
    }

    override fun friendDelete(id: String): Flow<Unit> {
        return friendDataSource.friendDelete(id = id)
    }
}