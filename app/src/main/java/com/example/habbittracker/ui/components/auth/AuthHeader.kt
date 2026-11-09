package com.example.habbittracker.ui.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.habbittracker.ui.components.BrandHeader
import com.example.habbittracker.ui.theme.CoinTextPrimary
import com.example.habbittracker.ui.theme.CoinTextSecondary


@Composable
fun AuthHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        BrandHeader()

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = CoinTextPrimary
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = CoinTextSecondary
        )
    }
}