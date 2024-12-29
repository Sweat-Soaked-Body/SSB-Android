package com.sweat.data.repository.profile

import com.sweat.model.profile.ProfileModel
import com.sweat.network.datasource.profile.ProfileDataSource
import com.sweat.network.dto.profile.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override fun getProfile(): Flow<ProfileModel> {
        return profileDataSource.getProfile().map { it.toModel() }
    }

    override fun updateProfile(): Flow<Unit> {
        return profileDataSource.updateProfile()
    }
}