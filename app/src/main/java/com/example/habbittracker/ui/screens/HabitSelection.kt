package com.example.habbittracker.ui.screens

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun HabitSection(onNextClick: () -> Unit) {
    Column() {
        Text("Что хочешь изменить?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,

            )
        Text("Выбери одну или несколько привычек",
        fontSize = 12.sp,
color = HabitTextPrimary
        )


    }
}