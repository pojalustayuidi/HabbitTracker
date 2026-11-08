package com.example.habbittracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = CoinGreen,
    onPrimary = Color.White,

    primaryContainer = CoinLightGreen,
    onPrimaryContainer = CoinDarkGreen,

    secondary = CoinDarkGreen,
    onSecondary = Color.White,

    background = CoinBackground,
    onBackground = CoinTextPrimary,

    surface = CoinSurface,
    onSurface = CoinTextPrimary,

    surfaceVariant = CoinLightGreen,
    onSurfaceVariant = CoinTextSecondary,

    outline = CoinBorder,

    error = CoinError,
    onError = Color.White,
    errorContainer = CoinErrorBackground
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF78C9A1),
    onPrimary = Color(0xFF003823),

    primaryContainer = CoinGreen,
    onPrimaryContainer = Color.White,

    secondary = Color(0xFFA4D9BC),
    onSecondary = Color(0xFF123B29),

    background = Color(0xFF101A15),
    onBackground = Color(0xFFF0F5EF),

    surface = Color(0xFF19251E),
    onSurface = Color(0xFFF0F5EF),

    surfaceVariant = Color(0xFF293B30),
    onSurfaceVariant = Color(0xFFB8C8BE),

    outline = Color(0xFF718579),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

@Composable
fun HabbitTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current

            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}