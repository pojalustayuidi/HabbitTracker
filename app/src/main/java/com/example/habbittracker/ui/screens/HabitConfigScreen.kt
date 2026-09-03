package com.example.habbittracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.data.models.HabitType
import com.example.habbittracker.viewmodel.HabitViewModel

@Composable
fun HabitConfigScreen(

    viewModel: HabitViewModel,
    onFinishClick: () -> Unit

){


  val selectedHabits = viewModel.selectedHabitsIds.collectAsState().value
    val badHabitsToConfigure =  HabitPresets.defaultHabits.filter { selectedHabits.contains(it.id) && it.type == HabitType.BAD_HABIT }
//    HabitTitle(title = "Сигареты пока так)")
    var costs by remember { mutableStateOf(mapOf<Int, String>()) }
    LazyColumn {
        items(badHabitsToConfigure, key = { habit -> habit.id }) { habit ->
            Text(text = habit.title)

        }
    }}