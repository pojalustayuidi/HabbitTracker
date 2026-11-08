package com.example.habbittracker.ui.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.text.KeyboardActions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock

import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import com.example.habbittracker.ui.components.registerScreen.CoinHabitTextField
import com.example.habbittracker.ui.theme.CoinTextSecondary


@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: String?,

    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String?,

    onSubmit: () -> Unit,

    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        CoinHabitTextField(
            title = "Email",
            value = email,

            onValueChange = onEmailChange,

            placeholder = "example@mail.com",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            errorMessage = emailError,

            leadingIcon = {

                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = null,
                    tint = CoinTextSecondary
                )
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        CoinHabitTextField(
            title = "Пароль",
            value = password,

            onValueChange = onPasswordChange,

            placeholder = "Введите пароль",
            keyboardType = KeyboardType.Password,
            isPassword = true,
            imeAction = ImeAction.Go,

            keyboardActions = KeyboardActions(
                onGo = {
                    onSubmit()
                }
            ),

            errorMessage = passwordError,

            leadingIcon = {

                Icon(
                    imageVector = Icons.Rounded.Lock,
                    contentDescription = null,
                    tint = CoinTextSecondary
                )
            }
        )
    }
}