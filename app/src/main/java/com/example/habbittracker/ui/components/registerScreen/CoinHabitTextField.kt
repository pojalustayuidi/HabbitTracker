package com.example.habbittracker.ui.components.registerScreen
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import com.example.habbittracker.ui.theme.CoinBorder
import com.example.habbittracker.ui.theme.CoinError
import com.example.habbittracker.ui.theme.CoinGreen
import com.example.habbittracker.ui.theme.CoinSurface
import com.example.habbittracker.ui.theme.CoinTextSecondary
import com.example.habbittracker.ui.theme.HabitGreen

@Composable
fun CoinHabitTextField(
    imeAction: ImeAction = ImeAction.Next,
    title: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    leadingIcon: (@Composable () -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMessage: String? = null


) {
    var passwordHiden by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,

            supportingText = if (errorMessage != null)   {
                {
                    Text(
                        text = errorMessage,
                        color = CoinError,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else null,
            isError = errorMessage != null,
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            visualTransformation = if (isPassword && !passwordHiden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    val image =
                        if (passwordHiden) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    IconButton(onClick = { passwordHiden = !passwordHiden }) {
                        Icon(imageVector = image, contentDescription = "Показать пароль")
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CoinGreen,
                unfocusedBorderColor = CoinBorder,
                errorBorderColor = CoinError,

                focusedContainerColor = CoinSurface,
                unfocusedContainerColor = CoinSurface,
                errorContainerColor = CoinSurface,

                cursorColor = CoinGreen,

                focusedPlaceholderColor = CoinTextSecondary,
                unfocusedPlaceholderColor = CoinTextSecondary
            )
        )

    }
}