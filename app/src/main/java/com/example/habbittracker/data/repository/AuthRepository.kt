package com.example.habbittracker.data.repository

import com.example.habbittracker.data.local.TokenManager
import com.example.habbittracker.data.remote.RetrofitClient
import com.example.habbittracker.data.remote.dto.AuthResponse
import com.example.habbittracker.data.remote.dto.LoginRequest
import com.example.habbittracker.data.remote.dto.RegisterRequest
import com.example.habbittracker.data.remote.dto.UserDto

class AuthRepository(private  val tokenManager: TokenManager) {
    private val api = RetrofitClient.getClient(tokenManager)
    suspend fun register(email: String, nickname: String, password: String): AuthResponse {
        val request = RegisterRequest(email, nickname, password)
        return api.register(request)
    }

    suspend fun login(email: String, password: String) : AuthResponse{
        val request = LoginRequest(email, password)
        return api.login(request)
    }

    suspend fun getMe() : UserDto{
        return api.getMe()
    }
}