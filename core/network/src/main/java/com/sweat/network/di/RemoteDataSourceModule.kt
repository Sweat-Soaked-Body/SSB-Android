package com.sweat.network.di

import com.sweat.network.datasource.auth.AuthDataSource
import com.sweat.network.datasource.auth.AuthDataSourceImpl
import com.sweat.network.datasource.auth.FriendDataSource
import com.sweat.network.datasource.auth.FriendDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindAuthRemoteDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    abstract fun bindFriendRemoteDataSource(
        friendDataSourceImpl: FriendDataSourceImpl
    ): FriendDataSource
}