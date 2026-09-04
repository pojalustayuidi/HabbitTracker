package com.example.habbittracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun SavingCard(amount: Int, modifier: Modifier = Modifier){
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(containerColor =HabitGreen)
    )
    {
        Column() {
            Text("Сэкономлено всего", color = HabitTextPrimary)
            Text("$amount ₽", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp
            )
            Text("+ 78   Сегодня")
        }
    }

}
