package com.example.habbittracker.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbittracker.data.local.TokenManager
import com.example.habbittracker.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ViewModel просит в конструктор репозиторий и менеджер токенов
class AuthViewModel(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    // Внутреннее состояние (мы можем его менять через .value)
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    // Внешнее состояние (экран может только читать его)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun register(email: String, nickname: String, password: String) {
        // Запускаем корутину, привязанную к жизненному циклу ViewModel
        viewModelScope.launch {
_authState.value = AuthState.Loading
            try {
                val response  = repository.register(email, nickname, password)
                tokenManager.saveToken(response.token)
                _authState.value = AuthState.Success



            } catch (e: Exception) {
                // Если произошла любая ошибка, мы попадаем сюда
                _authState.value = AuthState.Error(e.message ?: "Неизвестная ошибка" )
            }
        }
    }


    fun login(email: String, password: String){
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