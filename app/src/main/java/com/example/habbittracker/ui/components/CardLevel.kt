package com.example.habbittracker.ui.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.ui.theme.HabitBorder
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitGreenLight
import com.example.habbittracker.ui.theme.HabitSurface
import com.example.habbittracker.ui.theme.HabitTextPrimary
import com.example.habbittracker.ui.theme.HabitTextSecondary

@Composable
fun CardLevel(level: Int, xp: Int){
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = HabitSurface,

            ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = HabitBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Уровень $level",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = HabitTextPrimary,

                    )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$xp / 1000 XP", color = HabitTextSecondary,
                    fontSize = 12.sp,

                    )
            }
            LinearProgressIndicator(
                progress = {0.7f},
                modifier = Modifier
                    .padding(top = 12.dp)
                    .height(8.dp)
                    .fillMaxWidth(),

                color = HabitGreen,
                trackColor = HabitGreenLight,
                strokeCap = StrokeCap.Round,
                drawStopIndicator = {},
                gapSize = 0.dp
            )
        }

    }}
