package com.sweat.network.datasource.profile

import com.sweat.network.dto.profile.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun getProfile(): Flow<ProfileResponse>
    fun updateProfile(): Flow<Unit>
}
