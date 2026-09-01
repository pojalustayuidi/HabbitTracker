package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habbittracker.viewmodel.HabitViewModel
import com.example.habbittracker.viewmodel.HabitViewModelFactory
import com.example.habbittracker.data.local.HabitDatabase
import com.example.habbittracker.ui.components.CardLevel
import com.example.habbittracker.ui.components.GreetingSection
import com.example.habbittracker.ui.components.HabitItem
import com.example.habbittracker.ui.components.SavingCard
import com.example.habbittracker.ui.components.StatCard
import com.example.habbittracker.ui.theme.HabitAccent
import com.example.habbittracker.ui.theme.HabitFire
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel = viewModel(factory = HabitViewModelFactory(
        HabitDatabase.getDatabase(LocalContext.current).habitDao()
    )
    )
) {
    var newHabitName by remember { mutableStateOf("") }
val totalXp by viewModel.totalXp.collectAsState()
    val streak by viewModel.streak.collectAsState()
    val habits by viewModel.habits.collectAsState()
    val level = totalXp / 1000
    val newXp = totalXp % 1000

    Column(modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp))
    {
        Text(
            text = "CoinHabit",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = HabitGreen
        )
        GreetingSection(name = "Артём",  modifier = Modifier.padding(bottom = 5.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)  ){

            StatCard(
            icon = Icons.Default.LocalFireDepartment,
            iconTint = HabitFire,
            title = "Текущий стрик",
            value = streak,
            subtitle = "дней",
                modifier = Modifier.weight(1f)

            )
            StatCard(
                icon = Icons.Default.Star,
                iconTint = HabitAccent,
                title = "Монеты",
                value = totalXp,
                subtitle = "+12 сегодня",
                modifier = Modifier.weight(1f)


            ) }


        CardLevel(xp = newXp, level = level)
        Text(text = "Мои привычки", fontSize = 16.sp, color = HabitTextPrimary, fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(habits, key = { habit -> habit.id }) { habit ->
                HabitItem(habit, onToggle = {
                    viewModel.toogleHabit(habit.id)

                }, onDelete = {
                    viewModel.deleteHabit(habit.id)
                }
                )

            }
        }
        SavingCard(amount = 10)

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