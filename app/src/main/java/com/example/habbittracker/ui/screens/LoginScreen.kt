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
import com.example.habbittracker.ui.components.auth.LoginForm
import com.example.habbittracker.ui.theme.CoinBackground

import com.example.habbittracker.ui.theme.CoinGreen
import com.example.habbittracker.ui.theme.CoinTextSecondary


@Composable
fun LoginScreen(
    onNextClick: () -> Unit,
    viewModel: AuthViewModel,
    onRegisterClick: () -> Unit,
    onGuestClick: () -> Unit = {}
) {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val authState by viewModel.authState.collectAsState()

    val isLoading = authState is AuthState.Loading
    val serverError = authState as? AuthState.Error

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val submitLogin = {

        focusManager.clearFocus()

        keyboardController?.hide()

        viewModel.login(email, password)
    }

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

            AuthHeader(
                title = "Продолжим?",
                subtitle = "Твой прогресс уже ждёт тебя."
            )
            Spacer(
                modifier = Modifier.height(22.dp)
            )

            LoginForm(
                email = email,

                onEmailChange = {
                    email = it
                    viewModel.onLoginEmailEdited()
                },

                emailError = emailError,

                password = password,

                onPasswordChange = {
                    password = it
                    viewModel.onLoginPasswordEdited()
                },

                passwordError = passwordError,

                onSubmit = {
                    submitLogin()
                }
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )



            AuthErrorBanner(
                message = serverError?.message
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            AuthActionSection(
                primaryText = "Войти",
                loadingText = "Входим...",
                isLoading = isLoading,

                onPrimaryClick = {
                    submitLogin()
                },

                onGuestClick = onGuestClick
            )
            Spacer(
                modifier = Modifier.height(6.dp)
            )



            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Нет аккаунта?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CoinTextSecondary
                )

                TextButton(
                    enabled = !isLoading,

                    onClick = {
                        viewModel.resetState()
                        onRegisterClick()
                    }
                ) {

                    Text(
                        text = "Зарегистрироваться",
                        color = CoinGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}