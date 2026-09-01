package com.example.habbittracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habbittracker.ui.theme.HabitGreen

@Composable
fun HabitsCard(
title: String,
isSelected: Boolean,
onClick: () -> Unit
){
    OutlinedCard(
        shape = CardDefaults.shape,

        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) HabitGreen else MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.height(100.dp).clickable{onClick()}
           ){
        Text(title)

    }
}
