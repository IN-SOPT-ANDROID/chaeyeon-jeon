package org.sopt.sample.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.sopt.sample.api.AuthService
import org.sopt.sample.api.FollowerService
import retrofit2.Retrofit

object ApiFactory {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) // JSON -> Kotlin
            .build()
    }

    val retrofitReqres by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) // JSON -> Kotlin
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
    inline fun <reified T> createReqres(): T = retrofitReqres.create<T>(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val followerService = ApiFactory.createReqres<FollowerService>()
}