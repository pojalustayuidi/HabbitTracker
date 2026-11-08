package com.example.habbittracker.ui.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import com.example.habbittracker.ui.components.registerScreen.CoinHabitTextField
import com.example.habbittracker.ui.theme.CoinTextSecondary

@Composable
fun RegisterForm(
    name: String,
    email: String,
    password: String,

    nameError: String?,
    emailError: String?,
    passwordError: String?,

    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,

    onSubmit: () -> Unit,

    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxWidth()
    ) {


        CoinHabitTextField(
            title = "Имя",
            placeholder = "Как тебя зовут?",
            value = name,
            onValueChange = onNameChange,
            errorMessage = nameError,

            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = CoinTextSecondary
                )
            },

            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,

            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        CoinHabitTextField(
            title = "Email",
            placeholder = "example@mail.com",
            value = email,
            onValueChange = onEmailChange,
            errorMessage = emailError,

            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = CoinTextSecondary
                )
            },

            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,

            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        CoinHabitTextField(
            title = "Пароль",
            placeholder = "Введите пароль",
            value = password,
            onValueChange = onPasswordChange,
            errorMessage = passwordError,

            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = CoinTextSecondary
                )
            },

            keyboardType = KeyboardType.Password,
            isPassword = true,
            imeAction = ImeAction.Go,

            keyboardActions = KeyboardActions(
                onGo = {
                    onSubmit()
                }
            )
        )
    }
}