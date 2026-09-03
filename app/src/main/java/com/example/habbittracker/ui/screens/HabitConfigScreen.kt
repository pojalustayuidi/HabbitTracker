package com.example.habbittracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.data.models.HabitType
import com.example.habbittracker.ui.theme.HabitGreen
import com.example.habbittracker.viewmodel.HabitViewModel

@Composable
fun HabitConfigScreen(

    viewModel: HabitViewModel,
    onFinishClick: () -> Unit

) {


    val selectedHabits = viewModel.selectedHabitsIds.collectAsState().value
    val badHabitsToConfigure =
        HabitPresets.defaultHabits.filter { selectedHabits.contains(it.id) && it.type == HabitType.BAD_HABIT }
    var currentIndex by remember { mutableIntStateOf(0) }
    var costs by remember { mutableStateOf(mapOf<Int, String>()) }
    Column(modifier = Modifier
        .imePadding()
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        if (badHabitsToConfigure.isNotEmpty()) {

            val currentHabit = badHabitsToConfigure[currentIndex]
            val costString = costs[currentHabit.id] ?: "0"
            val amount = costString.toDoubleOrNull() ?: 0.0
            val calculateValue = kotlin.math.ceil(amount /30.0  ).toInt()
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                Text(text = "Шаг ${currentIndex + 1} из ${badHabitsToConfigure.size}")
            }

            Text(text = currentHabit.title, fontSize = 48.sp, fontWeight = FontWeight.SemiBold)

            Text(text = "Сколько ты обычно тратишь на это в месяц?₽", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = HabitGreen),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Введите сумму ₽") },
                suffix = {
                    IconButton(onClick = {costs = costs - currentHabit.id}) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Закрыть"
                        )
                    }
                },
                value = costs[currentHabit.id] ?: "",
                onValueChange = { newValue -> costs = costs + (currentHabit.id to newValue) })

            Text(text = "= $calculateValue ₽ в день", fontSize = 24.sp)
            Spacer(modifier = Modifier.weight(0.85f))
            Button(
                shape = ButtonDefaults.shape,
                colors = ButtonDefaults.buttonColors(containerColor = HabitGreen),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (currentIndex < badHabitsToConfigure.size - 1) {
                        currentIndex++
                    } else {
                        viewModel.saveConfiguredHabits(costs)
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
                onClick = {
                    viewModel.saveConfiguredHabits(costs)
                    onFinishClick()}
            ) {
                Text("Готово")
            }
        }
    }
}