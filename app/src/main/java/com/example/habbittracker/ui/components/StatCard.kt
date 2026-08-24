package com.example.habbittracker.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.habbittracker.ui.theme.HabitTextPrimary

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
    shape = CardDefaults.shape ,
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
){

    Row(verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = icon,
            contentDescription = title,
            tint = iconTint
        )
        Column(

            verticalArrangement = Arrangement.spacedBy(space = 8.dp),

        ) {

            Text(title, color = HabitTextPrimary)
           value
            Text(subtitle)
        }
    }
}
}