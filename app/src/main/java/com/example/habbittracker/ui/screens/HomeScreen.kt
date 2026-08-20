package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habbittracker.HabitViewModel
import com.example.habbittracker.ui.components.CardLevel
import com.example.habbittracker.ui.components.GreetingSection
import com.example.habbittracker.ui.components.HabitItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel = viewModel()
) {
    var newHabitName by remember { mutableStateOf("") }

    var totalXp by remember { mutableIntStateOf(0) }
    var dayCompleted by remember { mutableStateOf(false) }
    var streak by remember { mutableIntStateOf(0) }
    val level = totalXp / 1000
    val newXp = totalXp % 1000

    Column(modifier) {
        GreetingSection(name = "Артём", streak = streak, modifier = Modifier.padding(bottom = 5.dp))
        CardLevel(xp = newXp, level = level)
        LazyColumn {
            items(viewModel.habits, key = { habit -> habit.id }) { habit ->
                HabitItem(habit, onToggle = {
                    viewModel.toogleHabit(habit.id)

                }, onDelete = {
                    viewModel.deleteHabit(habit.id)
                }
                )

            }
        }
        Row {
            TextField(
                label = { Text("Новая привычка") },
                onValueChange = { newValue -> newHabitName = newValue }, value = newHabitName
            )
            IconButton(

                onClick
                = {
                    viewModel.addHabit(newHabitName)
                    newHabitName = ""


                }

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить привычку"
                )
            }
        }


        Button(
            onClick = {
                viewModel.completeDay()

            }
        ) {
            Text("Завершить д,ень")
        }
        Button(
            onClick = {
                viewModel.startNewDay()
            }
        ) {
            Text("Начать день")
        }

    }}