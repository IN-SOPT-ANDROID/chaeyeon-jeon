package org.sopt.sample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.repository.AuthRepository
import org.sopt.sample.data.repository.AuthRepositoryImpl
import org.sopt.sample.data.repository.FollowerRepository
import org.sopt.sample.data.repository.FollowerRepositoryImpl
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
}
