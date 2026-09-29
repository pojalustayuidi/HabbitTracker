package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.presentation.auth.AuthState
import com.example.habbittracker.presentation.auth.AuthViewModel
import com.example.habbittracker.ui.components.registerScreen.CoinHabitTextField
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextSecondary

@Composable
fun RegisterScreen(onNextClick: () -> Unit, viewModel: AuthViewModel, onLoginClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val nameError by viewModel.nameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()


    val authState by viewModel.authState.collectAsState()
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onNextClick()
        }
    }

    val scrollState = rememberScrollState()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .imePadding()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "CoinHabit",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = HabitGreen,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Создайте аккаунт",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Чтобы сохранить прогресс и не потерять его",
                fontSize = 16.sp,
                color = HabitTextSecondary
            )
            CoinHabitTextField(
                errorMessage = nameError,
                onValueChange = { name = it },
                placeholder = "Ваше имя",
                value = name,
                title = "Имя"
            )
            CoinHabitTextField(
                errorMessage = emailError,
                onValueChange = { email = it },
                placeholder = "example@mail.com",
                value = email,
                title = "Email"
            )
            CoinHabitTextField(
                errorMessage = passwordError,
                onValueChange = { password = it },
                placeholder = "******",
                value = password,
                title = "Пароль",
                isPassword = true,
                imeAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.register(
                        nickname = name,
                        email = email,
                        password = password
                    )

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = authState !is AuthState.Loading,
                colors = ButtonDefaults.buttonColors(containerColor = HabitGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (authState is AuthState.Loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Зарегистрироваться", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
            if (authState is AuthState.Error) {
                Text(
                    text = (authState as AuthState.Error).message,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)

                Text(
                    text = "или",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = HabitTextSecondary
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)

            }
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                onClick = { /* TODO: Google Auth */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Продолжить с Google", color = Color.Black, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    "Уже есть аккаунт?",
                    textAlign = TextAlign.Center,
                    color = HabitTextSecondary,
                    modifier = Modifier.fillMaxHeight()

                )
                TextButton(onClick = { onLoginClick()
                viewModel.resetState()
                }) {
                    Text("Войти", color = HabitGreen, fontWeight = FontWeight.Bold)
                }
            }

        }

    }
}

