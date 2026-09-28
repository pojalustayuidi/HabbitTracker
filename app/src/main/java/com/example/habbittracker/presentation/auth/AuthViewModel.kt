package com.example.habbittracker.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbittracker.data.local.TokenManager
import com.example.habbittracker.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {


    private val _emailError = MutableStateFlow<String?>(null)

    val emailError = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError = _passwordError.asStateFlow()
    private val _nameError = MutableStateFlow<String?>(null)
    val nameError = _nameError.asStateFlow()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun register(email: String, nickname: String, password: String) {
        _nameError.value = null
        _emailError.value = null
        _passwordError.value = null
        var isValid = true

        if (nickname.isBlank()) {
            _nameError.value = "Введите имя"
            isValid = false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "Некорректный Email"
            isValid = false

        }
        if (password.length < 6) {
            _passwordError.value = "Пароль должен состоять минимум из 6 символов"
            isValid = false
        }
        if (!isValid) return
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.register(email, nickname, password)
                tokenManager.saveToken(response.token)
                _authState.value = AuthState.Success


            } catch (e: retrofit2.HttpException) {
                val errorMessage = when (e.code()) {
                    400 -> "Ошибка в данных регистрации"
                    409 -> "Пользователь с таким email уже существует"
                    else -> "Ошибка сервера: ${e.code()}"
                }
                _authState.value = AuthState.Error(errorMessage)
            } catch (e: java.net.ConnectException) {
                _authState.value = AuthState.Error("Нет подключения к серверу.Проверьте сеть")
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }


    fun login(email: String, password: String) {
        _emailError.value = null
        _passwordError.value = null
        var isValid = true


        if (!email.contains("@")) {
            _emailError.value = "Некорректный Email"
            isValid = false

        }
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            try {
                val response = repository.login(email, password)
                tokenManager.saveToken(response.token)
                _authState.value = AuthState.Success
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Неизвестная ошибка")
            }

        }
    }
}