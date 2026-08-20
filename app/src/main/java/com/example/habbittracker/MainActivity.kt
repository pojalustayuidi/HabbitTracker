            package com.example.habbittracker

import  android.os.Bundle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Habit

            class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

    }


    @Composable
    fun GreetingSection(name: String, streak: Int, modifier: Modifier = Modifier) {
        Column {
            Text(
                text = "Привет $name!",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,

                modifier = modifier

            )
            Row {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = "Streak",
                    tint = Color.Red,
                    modifier = Modifier.size(21.dp)
                )
                Text(
                    text = "$streak  Стрик",
                    modifier = modifier
                )

            }

        }
    }


@Composable
fun CardLevel(level: Int, xp: Int){
    Column(Modifier.background(color = Color.Gray)) {
        Text(text = "Уровень $level")

        Text(text = "$xp / 1000 XP")
    }}


        @Composable
        fun HabitItem(habit: Habit, onToggle: () -> Unit, onDelete: () -> Unit){
            OutlinedCard(
                        modifier = Modifier.padding(vertical = 4.dp)

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier =  Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        )  {

                    Checkbox(modifier = Modifier.padding(horizontal = 6.dp),

                        checked = habit.done, onCheckedChange = {onToggle()}) //false → true
                    if (!habit.done){
                        Text(text = habit.name)
                    }
                    else {
                        Text(text = habit.name, modifier = Modifier.alpha(0.5f), textDecoration = TextDecoration.LineThrough )
                        Text(text =  "+ ${habit.xp} HP ", Modifier.padding(start = 25.dp))}
                    IconButton(
                        onClick = {
                            onDelete()
                        },
                    ){
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Удалить привычку")
                    }



                }

            }

        }





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
            CardLevel(xp = newXp, level = level )
            LazyColumn {
                items(viewModel.habits, key = { habit -> habit.id }) {
                        habit -> HabitItem(habit, onToggle = {viewModel.toogleHabit(habit.id)

                }, onDelete = {
                    viewModel.deleteHabit(habit.id)
                }
                        )

                }            }
Row {
    TextField(
        label = {Text("Новая привычка")},
        onValueChange = {newValue -> newHabitName = newValue}, value = newHabitName)
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







        }
    }
}

