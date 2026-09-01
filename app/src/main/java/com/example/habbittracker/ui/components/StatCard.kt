package com.example.habbittracker.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.ui.theme.HabitBorder
import com.example.habbittracker.ui.theme.HabitSurface
import com.example.habbittracker.ui.theme.HabitTextSecondary

@Composable

fun StatCard(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    value: Int,
    subtitle: String,
    modifier: Modifier = Modifier
)
{
OutlinedCard (modifier = modifier,
    border = BorderStroke(1.dp, HabitBorder),
    shape = RoundedCornerShape(16.dp) ,
    colors = CardDefaults.cardColors(
        containerColor = HabitSurface,

    ),

    ){

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(
            horizontal = 8.dp,
            vertical = 12.dp
        )
    ) {

        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = icon,
            contentDescription = title,
            tint = iconTint
        )
        Column(

            verticalArrangement = Arrangement.spacedBy(space = 2.dp),

        ) {

            Text(title, color = HabitTextSecondary, fontSize = 12.sp, maxLines = 1 )
            Text(value.toString(), fontWeight = FontWeight.Bold)
            Text(subtitle, color = HabitTextSecondary)
        }
    }
}
}