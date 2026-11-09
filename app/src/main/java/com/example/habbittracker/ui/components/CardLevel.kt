package com.example.habbittracker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habbittracker.ui.theme.*

@Composable
fun CardLevel(
    level: Int,
    xp: Int
) {
    val maxXp = 1000
    val progress = (xp.toFloat() / maxXp).coerceIn(0f, 1f)

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = CoinSurface
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, CoinBorder),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Уровень $level",
                    style = MaterialTheme.typography.titleMedium,
                    color = CoinTextPrimary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$xp / $maxXp XP",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CoinTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = CoinGreen,
                trackColor = CoinLightGreen,
                strokeCap = StrokeCap.Round,
                drawStopIndicator = {},
                gapSize = 0.dp
            )
        }
    }
}

@Preview(
    name = "CoinHabit - Уровень",
    showBackground = true,
    backgroundColor = 0xFFFAF8F2
)
@Composable
private fun CardLevelPreview() {
    HabbitTrackerTheme(darkTheme = false) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            CardLevel(
                level = 5,
                xp = 700
            )
        }
    }
}