package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.data.models.HabitType
import com.example.habbittracker.viewmodel.HabitViewModel

@Composable
fun HabitConfigScreen(

    viewModel: HabitViewModel,
    onFinishClick: () -> Unit

) {


    val selectedHabits = viewModel.selectedHabitsIds.collectAsState().value
    val badHabitsToConfigure =
        HabitPresets.defaultHabits.filter { selectedHabits.contains(it.id) && it.type == HabitType.BAD_HABIT }
    //    HabitTitle(title = "Сигареты пока так)")
    var currentIndex by remember { mutableStateOf(0) }
    var costs by remember { mutableStateOf(mapOf<Int, String>()) }
    Column {
        if (badHabitsToConfigure.isNotEmpty()) {

            val currentHabit = badHabitsToConfigure[currentIndex]

            Text(text = "Шаг ${currentIndex + 1} из ${badHabitsToConfigure.size}")

            Text(text = currentHabit.title)


            OutlinedTextField(

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Введите сумму") },
                suffix = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Закрыть"
                        )
                    }
                },
                value = costs[currentHabit.id] ?: "",
                onValueChange = { newValue -> costs = costs + (currentHabit.id to newValue) })
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (currentIndex < badHabitsToConfigure.size - 1) {
                        currentIndex++
                    } else {
                        onFinishClick()
                    }
                }
            ) {
                if (currentIndex < badHabitsToConfigure.size - 1) {
                    Text("Далее (${currentIndex + 1} / ${badHabitsToConfigure.size})")

                } else {
                    Text("Создать привычки")
                }
            }

        } else {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onFinishClick
            ) {
                Text("Готово")
            }
        }
    }
}