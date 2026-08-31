package com.example.habbittracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun SavingCard(modifier: Modifier = Modifier, text: String){
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(containerColor =HabitGreen)
    )
    {
        Column(modifier = modifier.padding(16.dp)) {
            Text("Сэкономлено всего", color = HabitTextPrimary, fontSize = 14.sp)
            Text(
                text = "$text ₽",
                color = HabitTextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }

}
