package com.sweat.data.di

import com.sweat.data.repository.auth.AuthRepository
import com.sweat.data.repository.auth.AuthRepositoryImpl
import com.sweat.data.repository.friend.FriendRepository
import com.sweat.data.repository.friend.FriendRepositoryImpl
import com.sweat.data.repository.profile.ProfileRepository
import com.sweat.data.repository.profile.ProfileRepositoryImpl
import com.sweat.network.datasource.profile.ProfileDataSource
import com.sweat.network.datasource.profile.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindFriendRepository(
        friendRepositoryImpl: FriendRepositoryImpl
    ): FriendRepository

    @Binds
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}