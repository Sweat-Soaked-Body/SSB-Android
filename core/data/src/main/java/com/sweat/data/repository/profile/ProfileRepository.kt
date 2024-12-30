package com.sweat.data.repository.profile

import com.sweat.model.profile.ProfileModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(): Flow<ProfileModel>
    fun updateProfile(): Flow<Unit>
}
