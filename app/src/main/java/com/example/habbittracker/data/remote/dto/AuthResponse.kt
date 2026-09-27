package com.example.habbittracker.data.remote.dto

data class AuthResponse(
    val token: String,
    val user: UserDto
)