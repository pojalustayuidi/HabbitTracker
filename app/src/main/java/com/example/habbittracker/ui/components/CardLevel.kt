package com.example.habbittracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CardLevel(level: Int, xp: Int){
    Column(Modifier.background(color = Color.Gray)) {
        Text(text = "Уровень $level")

        Text(text = "$xp / 1000 XP")
    }}
