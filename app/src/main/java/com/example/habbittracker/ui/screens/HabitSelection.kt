package com.example.habbittracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun HabitSection(onNextClick: () -> Unit) {
    var selectedHabitsIds by remember { mutableStateOf(emptySet<Int>()) }
    Column {
        Text(
            "Что хочешь изменить?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,

            )
        Text(
            "Выбери одну или несколько привычек",
            fontSize = 12.sp,
            color = HabitTextPrimary
        )

        LazyColumn {
            items(HabitPresets.defaultHabits) { item ->
                val isSelected = selectedHabitsIds.contains(item.id)
                Card(

                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) HabitGreen else MaterialTheme.colorScheme.surfaceVariant
                    ), modifier = Modifier
                        .clickable

                        {
                            selectedHabitsIds = if (selectedHabitsIds.contains(item.id)) {
                                selectedHabitsIds - item.id
                            } else selectedHabitsIds + item.id

                        }) {
                    Text(text = item.title)

                }

            }

        }

        Button(
            colors = ButtonDefaults.buttonColors(HabitGreen),
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextClick

        ) {
            val buttonText = if (selectedHabitsIds.isEmpty()) {
                "Продолжить"
            } else {
                "Начать (${selectedHabitsIds.size})"
            }

            Text(text = buttonText)

        }

    }
}