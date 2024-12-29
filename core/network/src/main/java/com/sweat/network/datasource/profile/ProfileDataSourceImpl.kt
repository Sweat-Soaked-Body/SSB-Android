package com.sweat.network.datasource.profile

import com.sweat.network.api.ProfileApi
import com.sweat.network.dto.profile.ProfileBody
import com.sweat.network.util.performApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileApi: ProfileApi
) : ProfileDataSource {
    override fun getProfile(body: ProfileBody): Flow<Unit> =
        performApiRequest { profileApi.getProfile(body = body) }

    override fun updateProfile(): Flow<Unit> =
        performApiRequest { profileApi.updateProfile() }
}
