package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.api.AuthService
import org.sopt.sample.api.FollowerService
import org.sopt.sample.api.MusicService
import org.sopt.sample.data.local.BaseUrlType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    fun providesAuthService(@RetrofitModule.Retrofit2(BaseUrlType.IN_SOPT) retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun providesFollowerService(@RetrofitModule.Retrofit2(BaseUrlType.REQRES) retrofit: Retrofit): FollowerService =
        retrofit.create(FollowerService::class.java)

    @Provides
    fun providesMusicService(@RetrofitModule.Retrofit2(BaseUrlType.MUSIC) retrofit: Retrofit): MusicService =
        retrofit.create(MusicService::class.java)
}
