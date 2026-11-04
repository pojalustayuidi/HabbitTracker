package com.example.habbittracker.data.remote.dto

data class RegisterRequest(
    val email: String,
    val nickname: String,
    val password: String
)
