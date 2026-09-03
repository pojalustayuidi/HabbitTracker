package com.example.habbittracker.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HabitTitle(
    title: String,
){

    Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
}