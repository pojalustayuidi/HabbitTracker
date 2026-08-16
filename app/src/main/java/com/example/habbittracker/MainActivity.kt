            package com.example.habbittracker

import  android.os.Bundle
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
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
                modifier = modifier
            )
            Text(
                text = "$streak  Стрик",
                modifier = modifier
            )
        }
    }


@Composable
fun CardLevel(level: Int, xp: Int){
    Column(Modifier.background(color = Color.Gray)) {
        Text(text = "Уровень $level")

        Text(text = "$xp / 1000 XP")
    }}


        @Composable
        fun HabitItem(habit: Habit, onToggle: () -> Unit){


            Row(
                modifier =  Modifier
                    .background(Color.Cyan)
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable
                    {
                        onToggle()
                    })  {
              Checkbox(checked = habit.done, onCheckedChange = null) //false → true
if (!habit.done){
   Text(text = habit.name)
}
                else {
        Text(text = habit.name, modifier = Modifier.alpha(0.5f), textDecoration = TextDecoration.LineThrough )
                Text(text =  "+ ${habit.xp} HP ", Modifier.padding(start = 25.dp))}


            }
        }





    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {

        // Состояние списка привычек.
// Изменяем список целиком, а не отдельный Habit.


        var habits by remember { mutableStateOf(
            listOf(
                Habit("Пробежка", 10, false, id = 1),
                Habit("Курение", 20, false, id = 2),
                Habit("Душ", 10, false, id = 3),

                )
        ) }

        val completedHabit = habits.filter { it.done }
        val allXp = completedHabit.sumOf { it.xp  }
        val allDone = habits.all { it.done }



//        val totalXp = runningXp + smokingXp + showerXp
        var totalXp by remember { mutableIntStateOf(0) }
        var dayCompleted by remember { mutableStateOf(false) }
var streak by remember { mutableIntStateOf(0) }
val level = totalXp / 1000
        val newXp = totalXp % 1000

        Column(modifier) {
            GreetingSection(name = "Артём", streak = streak, modifier = Modifier.padding(bottom = 5.dp))
            CardLevel(xp = newXp, level = level )
            LazyColumn {
                items(habits, key = { habit -> habit.id }) {
                        habit -> HabitItem(habit) {
                            habits = habits.map { habitsFromList ->
                                if (habitsFromList.id == habit.id){
                                    habitsFromList.copy(done = !habitsFromList.done)
                                } else{
                                    habitsFromList
                                }

                            }
                }
                }            }

            Button(
                onClick = {
if (allDone && !dayCompleted){
    totalXp += allXp
    dayCompleted = true
    streak++

}

                }
            ) {
                    Text("Завершить д,ень")
                }
                Button(
                    onClick = {
                        if (!dayCompleted) {
                            streak = 0
                        }
                        dayCompleted =false
                        habits =  habits.map { clearHabits ->  clearHabits.copy(done = false)
                        }

                    }
                ) {
                    Text("Начать день")
                }







        }
    }
}

