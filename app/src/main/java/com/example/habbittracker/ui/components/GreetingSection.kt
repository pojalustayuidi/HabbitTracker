package com.example.habbittracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingSection(name: String, streak: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Привет $name!",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,

            modifier = modifier

        )
        Row {
            Icon(
                imageVector = Icons.Default.LocalFireDepartment,
                contentDescription = "Streak",
                tint = Color.Red,
                modifier = Modifier.size(21.dp)
            )
            Text(
                text = "$streak  Стрик",
                modifier = modifier
            )

        }

    }
}
