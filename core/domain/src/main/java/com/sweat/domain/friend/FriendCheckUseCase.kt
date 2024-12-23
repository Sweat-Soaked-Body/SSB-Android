package com.sweat.domain.friend

import com.sweat.data.repository.friend.FriendRepository
import javax.inject.Inject

class FriendCheckUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    operator fun invoke() = runCatching {
        friendRepository.friendCheck()
    }
}
