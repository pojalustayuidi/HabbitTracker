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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.viewmodel.HabitViewModel
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.data.models.HabitType
import com.example.habbittracker.ui.components.HabitsCard
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun HabitSection(onNextClick: () -> Unit, viewModel: HabitViewModel) {
    val selectedHabits = viewModel.selectedHabitsIds.collectAsState().value
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
                item ->  val isSelected = selectedHabits.contains(item.id)
                HabitsCard(
                    title = item.title,
                    isSelected = isSelected,
                    onClick = {
                   viewModel.toggleHabitSelection(item.id)
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
    item ->  val isSelected = selectedHabits.contains(item.id)
    HabitsCard(
        title = item.title,
        isSelected = isSelected,
        onClick = {
            viewModel.toggleHabitSelection(item.id)
        }
    )
}

        }

        Button(
            colors = ButtonDefaults.buttonColors(HabitGreen),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
//                viewModel.saveSelectedHabit(selectedHabits)
                onNextClick()

            }

        ) {
            val buttonText = if (selectedHabits.isEmpty()) {
                "Продолжить"
            } else {
                "Начать (${selectedHabits.size})"
            }

            Text(text = buttonText)

        }

    }
}