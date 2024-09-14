package com.sweat.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
annotation class RepositoryModule {
    // todo : Add bindRepository Elements
}