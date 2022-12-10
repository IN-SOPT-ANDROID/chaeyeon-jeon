package org.sopt.sample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.repository.* // ktlint-disable no-wildcard-imports
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun providesFollowerRepository(
        followerRepositoryImpl: FollowerRepositoryImpl
    ): FollowerRepository

    @Binds
    @Singleton
    abstract fun providesMusicRepository(
        musicRepositoryImpl: MusicRepositoryImpl
    ): MusicRepository
}
