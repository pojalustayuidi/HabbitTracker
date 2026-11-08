package com.example.habbittracker.ui.components.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.example.habbittracker.ui.theme.CoinBorder
import com.example.habbittracker.ui.theme.CoinGreen
import com.example.habbittracker.ui.theme.CoinTextSecondary


@Composable
fun AuthActionSection(
    primaryText: String,
    loadingText: String,
    isLoading: Boolean,
    onPrimaryClick: () -> Unit,
    onGuestClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Button(
            onClick = onPrimaryClick,
            enabled = !isLoading,

            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = CoinGreen,
                contentColor = Color.White,

                disabledContainerColor = CoinGreen.copy(
                    alpha = 0.88f
                ),

                disabledContentColor = Color.White
            )
        ) {

            if (isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = loadingText,
                    style = MaterialTheme.typography.labelLarge
                )

            } else {

                Text(
                    text = primaryText,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = CoinBorder
            )

            Text(
                text = "или",

                modifier = Modifier.padding(
                    horizontal = 16.dp
                ),

                style = MaterialTheme.typography.bodyMedium,
                color = CoinTextSecondary
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = CoinBorder
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedButton(
            onClick = onGuestClick,
            enabled = !isLoading,

            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),

            shape = RoundedCornerShape(14.dp),

            border = BorderStroke(
                width = 1.dp,
                color = CoinBorder
            ),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = CoinGreen
            )
        ) {

            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = "Продолжить как гость",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Без email и пароля",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelMedium,
            color = CoinTextSecondary,
            textAlign = TextAlign.Center
        )
    }
}