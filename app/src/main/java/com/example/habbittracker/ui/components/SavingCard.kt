package com.example.habbittracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.ui.theme.HabitGreen


@Composable
fun SavingCard(amount: Int, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HabitGreen)
    )
    {
        Column(
            Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text("Сэкономлено всего", color = Color.White, fontSize = 12.sp)
            Text(
                "$amount ₽", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, color = Color.White
            )
            Text("+ 78 ₽  cегодня",  fontSize = 12.sp, color = Color.White)
        }
    }

}
