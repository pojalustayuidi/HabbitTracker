package com.example.habbittracker.data.remote

import com.example.habbittracker.data.local.TokenManager
import com.example.habbittracker.data.models.Habit
import com.example.habbittracker.data.remote.api.AuthApi
import com.example.habbittracker.data.remote.api.HabitApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://waking-rewire-deviator.ngrok-free.dev/" //localhost*

    fun getClient(tokenManager: TokenManager): AuthApi{
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(tokenManager)).build()

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(AuthApi::class.java)


    }
    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    val habitApi: HabitApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HabitApi::class.java)
    }
}