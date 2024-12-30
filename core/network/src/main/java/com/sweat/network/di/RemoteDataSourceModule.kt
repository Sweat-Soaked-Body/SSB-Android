package com.sweat.network.di

import com.sweat.network.datasource.auth.AuthDataSource
import com.sweat.network.datasource.auth.AuthDataSourceImpl
import com.sweat.network.datasource.exercise.ExerciseDataSource
import com.sweat.network.datasource.exercise.ExerciseDataSourceImpl
import com.sweat.network.datasource.friend.FriendDataSource
import com.sweat.network.datasource.friend.FriendDataSourceImpl
import com.sweat.network.datasource.profile.ProfileDataSource
import com.sweat.network.datasource.profile.ProfileDataSourceImpl
import com.sweat.network.datasource.main.MainDataSource
import com.sweat.network.datasource.main.MainDataSourceImpl
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

    @Binds
    abstract fun bindMainRemoteDataSource(
        mainDataSourceImpl: MainDataSourceImpl
    ):MainDataSource

    @Binds
    abstract fun bindProfileRemoteDataSource(
        profileDataSourceImpl: ProfileDataSourceImpl
    ): ProfileDataSource

    @Binds
    abstract fun bindExerciseDataSource(
        exerciseDataSourceImpl: ExerciseDataSourceImpl
    ): ExerciseDataSource
}