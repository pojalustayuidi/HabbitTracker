package com.example.habbittracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() }
           ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Text(title, )
        }

    }
}
