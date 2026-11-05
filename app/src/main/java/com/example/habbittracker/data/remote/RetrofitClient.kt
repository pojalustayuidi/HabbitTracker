package com.example.habbittracker.data.remote

import com.example.habbittracker.data.local.TokenManager
import com.example.habbittracker.data.remote.api.AuthApi
import com.example.habbittracker.data.remote.api.HabitApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Локальная разработка через adb reverse.
    private const val AUTH_BASE_URL = "http://localhost:8080/"
    private const val HABIT_BASE_URL = "http://localhost:8081/"

    private fun createRetrofit(
        baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val publicClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    fun getClient(tokenManager: TokenManager): AuthApi {
        val authenticatedClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()

        return createRetrofit(
            baseUrl = AUTH_BASE_URL,
            client = authenticatedClient
        ).create(AuthApi::class.java)
    }

    val authApi: AuthApi by lazy {
        createRetrofit(
            baseUrl = AUTH_BASE_URL,
            client = publicClient
        ).create(AuthApi::class.java)
    }

    val habitApi: HabitApi by lazy {
        createRetrofit(
            baseUrl = HABIT_BASE_URL,
            client = publicClient
        ).create(HabitApi::class.java)
    }
}