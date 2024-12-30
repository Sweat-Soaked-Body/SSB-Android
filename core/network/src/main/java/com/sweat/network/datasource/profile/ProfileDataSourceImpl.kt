package com.sweat.network.datasource.profile

import com.sweat.network.api.ProfileApi
import com.sweat.network.dto.profile.ProfileResponse
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileDataSource {
    override fun getProfile(): Flow<ProfileResponse> =
        performApiRequest { profileApi.getProfile() }

    override fun updateProfile(): Flow<Unit> =
        performApiRequest { profileApi.updateProfile() }
}
