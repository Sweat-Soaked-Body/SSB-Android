package com.sweat.domain.friend

import com.sweat.data.repository.friend.FriendRepository
import javax.inject.Inject

class FriendDeleteUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    operator fun invoke(id: String) = runCatching {
        friendRepository.friendDelete(id = id)
    }
}
