package com.sweat.network.datasource.profile

import com.sweat.network.dto.profile.ProfileBody
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun getProfile(body: ProfileBody): Flow<Unit>
    fun updateProfile(): Flow<Unit>
}
