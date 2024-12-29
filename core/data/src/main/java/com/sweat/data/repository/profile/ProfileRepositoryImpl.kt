package com.sweat.data.repository.profile

import com.sweat.model.profile.ProfileModel
import com.sweat.network.datasource.profile.ProfileDataSource
import com.sweat.network.dto.profile.toDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override fun getProfile(body: ProfileModel): Flow<Unit> {
        return profileDataSource.getProfile(body = body.toDto())
    }

    override fun updateProfile(): Flow<Unit> {
        return profileDataSource.updateProfile()
    }
}