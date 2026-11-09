package com.example.habbittracker.ui.components.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.habbittracker.ui.theme.CoinError
import com.example.habbittracker.ui.theme.CoinErrorBackground


@Composable
fun AuthErrorBanner(
    message: String?,
    modifier: Modifier = Modifier
) {

    AnimatedContent(
        targetState = message,

        modifier = modifier.fillMaxWidth(),

        transitionSpec = {

            (
                    fadeIn(
                        animationSpec = tween(180)
                    ) + expandVertically(
                        animationSpec = tween(220)
                    )
                    ).togetherWith(

                    fadeOut(
                        animationSpec = tween(120)
                    ) + shrinkVertically(
                        animationSpec = tween(180)
                    )
                )
        },

        label = "auth_error_animation"
    ) { errorMessage ->

        if (errorMessage != null) {

            Column {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = CoinErrorBackground
                ) {

                    Row(
                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 12.dp
                        ),

                        verticalAlignment = Alignment.CenterVertically,

                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.ErrorOutline,
                            contentDescription = null,
                            tint = CoinError,
                            modifier = Modifier.size(20.dp)
                        )

                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = CoinError
                        )
                    }
                }
            }
        }
    }
}