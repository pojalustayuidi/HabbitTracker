package com.example.habbittracker.data.remote.api

import com.example.habbittracker.data.remote.dto.AuthResponse
import com.example.habbittracker.data.remote.dto.LoginRequest
import com.example.habbittracker.data.remote.dto.RegisterRequest
import com.example.habbittracker.data.remote.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @GET("api/auth/me")
    suspend fun getMe(): UserDto
}