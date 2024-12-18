package com.sweat.network.datasource.auth

import com.sweat.network.api.FriendApi
import com.sweat.network.dto.friend.AddFriendRequest
import com.sweat.network.dto.friend.FriendCheckRequest
import com.sweat.network.dto.friend.FriendResponse
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendDataSourceImpl @Inject constructor(
    private val serviceAuth: FriendApi
) : FriendDataSource {
    override fun friendCheck(body: FriendCheckRequest): Flow<FriendResponse> =
        performApiRequest { serviceAuth.friendCheck(body = body) }


    override fun friendAdd(body: AddFriendRequest): Flow<Unit> =
        performApiRequest { serviceAuth.friendAdd(body = body) }

    override fun friendDelete(id: String): Flow<Unit> =
        performApiRequest { serviceAuth.friendDelete(id = id) }
}
