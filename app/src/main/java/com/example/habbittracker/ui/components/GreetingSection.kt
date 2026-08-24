package com.example.habbittracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun GreetingSection(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Привет $name!",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,

            modifier = modifier

        )
        Text("Держишься отлично!")


    }
}
