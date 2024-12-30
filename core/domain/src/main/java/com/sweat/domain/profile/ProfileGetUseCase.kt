package com.sweat.domain.profile

import com.sweat.data.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileGetUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke() = runCatching {
        profileRepository.getProfile()
    }
}
