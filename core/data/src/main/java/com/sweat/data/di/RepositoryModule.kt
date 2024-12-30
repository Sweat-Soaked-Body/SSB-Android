package com.sweat.data.di

import com.sweat.data.repository.auth.AuthRepository
import com.sweat.data.repository.auth.AuthRepositoryImpl
import com.sweat.data.repository.exercise.ExerciseRepository
import com.sweat.data.repository.exercise.ExerciseRepositoryImpl
import com.sweat.data.repository.friend.FriendRepository
import com.sweat.data.repository.friend.FriendRepositoryImpl
import com.sweat.data.repository.main.MainRepository
import com.sweat.data.repository.main.MainRepositoryImpl
import com.sweat.data.repository.profile.ProfileRepository
import com.sweat.data.repository.profile.ProfileRepositoryImpl
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
    abstract fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository

    @Binds
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    abstract fun bindExerciseRepository(
        exerciseRepositoryImpl: ExerciseRepositoryImpl
    ): ExerciseRepository
}