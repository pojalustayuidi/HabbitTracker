package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.example.habbittracker.presentation.auth.AuthState
import com.example.habbittracker.presentation.auth.AuthViewModel

import com.example.habbittracker.ui.components.auth.AuthActionSection
import com.example.habbittracker.ui.components.auth.AuthErrorBanner
import com.example.habbittracker.ui.components.auth.AuthHeader
import com.example.habbittracker.ui.components.auth.RegisterForm

import com.example.habbittracker.ui.theme.CoinBackground
import com.example.habbittracker.ui.theme.CoinGreen
import com.example.habbittracker.ui.theme.CoinTextSecondary

@Composable
fun RegisterScreen(
    onNextClick: () -> Unit,
    viewModel: AuthViewModel,
    onLoginClick: () -> Unit
) {

    // Данные формы
    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    // Ошибки полей
    val nameError by viewModel.nameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()

    // Состояние авторизации
    val authState by viewModel.authState.collectAsState()

    val isLoading = authState is AuthState.Loading
    val serverError = authState as? AuthState.Error

    // Управление клавиатурой
    val keyboardController =
        LocalSoftwareKeyboardController.current

    val focusManager =
        LocalFocusManager.current

    // Единая отправка регистрации
    val submitRegistration: () -> Unit = {

        focusManager.clearFocus()

        keyboardController?.hide()

        if (!isLoading) {
            viewModel.register(
                nickname = name,
                email = email,
                password = password
            )
        }
    }

    // Переход после успешной регистрации
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onNextClick()
        }
    }

    Scaffold(
        containerColor = CoinBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Шапка
            AuthHeader(
                title = "Начнём?",
                subtitle = "Твои цели начинаются здесь."
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // Форма регистрации
            RegisterForm(
                name = name,
                email = email,
                password = password,

                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,

                onNameChange = {
                    name = it
                    viewModel.onRegisterNameEdited()
                },

                onEmailChange = {
                    email = it
                    viewModel.onRegisterEmailEdited()
                },

                onPasswordChange = {
                    password = it
                    viewModel.onRegisterPasswordEdited()
                },

                onSubmit = {
                    submitRegistration()
                }
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // Анимированная ошибка сервера
            AuthErrorBanner(
                message = serverError?.message
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // Кнопка регистрации
            AuthActionSection(
                primaryText = "Зарегистрироваться",
                loadingText = "Создаём аккаунт...",
                isLoading = isLoading,

                onPrimaryClick = {
                    submitRegistration()
                },

                // Гостевой режим добавим позже
                onGuestClick = null
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // Переход к экрану входа
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Уже есть аккаунт?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CoinTextSecondary
                )

                TextButton(
                    enabled = !isLoading,

                    onClick = {
                        viewModel.resetState()
                        onLoginClick()
                    }
                ) {

                    Text(
                        text = "Войти",
                        color = CoinGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}