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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.viewmodel.HabitViewModel
import com.example.habbittracker.ui.components.CardLevel
import com.example.habbittracker.ui.components.GreetingSection
import com.example.habbittracker.ui.components.HabitItem
import com.example.habbittracker.ui.components.SavingCard
import com.example.habbittracker.ui.components.StatCard
import com.example.habbittracker.ui.theme.HabitAccent
import com.example.habbittracker.ui.theme.HabitFire
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitTextPrimary
import com.example.habbittracker.ui.theme.HabitTextSecondary

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel,
    onAddHabitClick: () -> Unit
) {
    val totalXp by viewModel.totalXp.collectAsState()
    val streak by viewModel.streak.collectAsState()
    val habits by viewModel.habits.collectAsState()
    val level = totalXp / 1000
    val newXp = totalXp % 1000
   val  totalSavedMoney by viewModel.totalSavedMoney.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddHabitClick,
                containerColor = HabitGreen
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "CoinHabit",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = HabitGreen
            )

            GreetingSection(name = "Артём", modifier = Modifier.padding(bottom = 5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                )
            }

            CardLevel(xp = newXp, level = level)
            SavingCard(amount = totalSavedMoney)

            Text(
                text = "Мои привычки",
                fontSize = 16.sp,
                color = HabitTextPrimary,
                fontWeight = FontWeight.SemiBold
            )

            if (habits.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "У вас пока нет активных привычек. Добавьте новую, нажав на плюс.",
                        color = HabitTextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(habits, key = { habit -> habit.id }) { habit ->
                        HabitItem(
                            habit = habit,
                            onToggle = { viewModel.toggleHabit(habit.id) },
                            onDelete = { viewModel.deleteHabit(habit.id) }
                        )
                    }
                }
            }



        }
    }
}