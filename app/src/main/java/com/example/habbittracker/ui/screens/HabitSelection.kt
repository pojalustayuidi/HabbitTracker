package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.HabitViewModel
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.data.HabitType
import com.example.habbittracker.ui.components.HabitsCard
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun HabitSection(onNextClick: () -> Unit, viewModel: HabitViewModel) {

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

        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Fixed(2)
        ) {
            val badHabit = HabitPresets.defaultHabits.filter { it.type == HabitType.BAD_HABIT }
            val goodHabits = HabitPresets.defaultHabits.filter { it.type == HabitType.GOOD_HABIT }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = "Отказ от вредного (Экономия)",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            //Карточки вредных привычек
            items(badHabit){
                item ->  val isSelected = selectedHabitsIds.contains(item.id)
                HabitsCard(
                    title = item.title,
                    isSelected = isSelected,
                    onClick = {
                        selectedHabitsIds = if (isSelected){
                            selectedHabitsIds - item.id
                        }else
                        {
                            selectedHabitsIds + item.id
                        }
                    }
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = "Развитие (полезные привычки",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
items(goodHabits){
    item ->  val isSelected = selectedHabitsIds.contains(item.id)
    HabitsCard(
        title = item.title,
        isSelected = isSelected,
        onClick = {
            selectedHabitsIds = if (isSelected){
                selectedHabitsIds - item.id
            }else
            {
                selectedHabitsIds + item.id
            }
        }
    )
}

        }

        Button(
            colors = ButtonDefaults.buttonColors(HabitGreen),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.saveSelectedHabit(selectedHabitsIds)
                onNextClick()

            }

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