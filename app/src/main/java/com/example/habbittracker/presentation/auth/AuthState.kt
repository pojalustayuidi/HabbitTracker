package com.example.habbittracker.presentation.auth

sealed class AuthState {

    object Idle: AuthState() // Начальное состояние, открыл экран

    object  Loading: AuthState()

    object Success : AuthState()

    data class Error(val message: String) : AuthState()
}