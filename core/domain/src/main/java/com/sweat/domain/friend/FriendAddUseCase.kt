package com.sweat.domain.friend

import com.sweat.data.repository.friend.FriendRepository
import javax.inject.Inject

class FriendAddUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    operator fun invoke(name: String) = runCatching {
        friendRepository.friendAdd(name = name)
    }
}
