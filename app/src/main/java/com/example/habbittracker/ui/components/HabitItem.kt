package com.example.habbittracker.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.data.models.Habit
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.ui.theme.HabitGreenLight
import com.example.habbittracker.ui.theme.HabitTextSecondary

@Composable
fun HabitItem(habit: Habit, onToggle: () -> Unit, onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    OutlinedCard(
        modifier = Modifier.padding(vertical = 8.dp)

    ) {
        Column(
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.Red)

                )
                {
                    Icon(
                        contentDescription = "Иконка привычки", imageVector = Icons.Rounded.Star,
                        modifier = Modifier.size(40.dp)
                    )

                }

                if (!habit.done) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = habit.name)
                        Text(text = "5/7 дней", color = HabitTextSecondary, fontSize = 12.sp)
                    }
                } else {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = habit.name,
                            modifier = Modifier.alpha(0.5f),
                            textDecoration = TextDecoration.LineThrough
                        )
                        Text(text = "+${habit.xp} XP", color = HabitGreen, fontSize = 12.sp)
                    }
                }
                IconButton(onClick = onToggle) {
                    Icon(
                        imageVector = if (habit.done) {
                            Icons.Rounded.CheckCircle
                        } else {
                            Icons.Rounded.RadioButtonUnchecked
                        },
                        contentDescription = "Отметить привычку",
                        tint = if (habit.done) HabitGreen else HabitTextSecondary,
                        modifier = Modifier.size(28.dp)
                    )
                }


                Box {
                    IconButton(
                        onClick = {
                            expanded = true
                        },
                    )
                    {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Редактировать") },
                                onClick = { expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Отметить срыв") },
                                onClick = { expanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Удалить привычку") },
                                onClick = {
                                    expanded = false
                                    onDelete()
                                }
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Удалить привычку"
                        )
                    }
                }

            }
            LinearProgressIndicator(
                progress = { 0.7f },
                modifier = Modifier
                    .height(8.dp)
                    .padding(horizontal = 70.dp)
                    .fillMaxWidth(),

                color = HabitGreen,
                trackColor = HabitGreenLight,
                strokeCap = StrokeCap.Round,
                drawStopIndicator = {},
                gapSize = 0.dp
            )
        }

    }

}