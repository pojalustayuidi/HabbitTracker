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
        fun HabitItem(habbit: String, xp: Int, done: Boolean, onToggle: () -> Unit){

            Row(
                modifier =  Modifier
                    .background(Color.Cyan)
                    .padding(5.dp)
                    .fillMaxWidth()
                .clickable()
                {
                onToggle()
            }) {
                Checkbox(checked = done, onCheckedChange = null) //false → true
if (!done){
    Text(text = habbit)
}
                else (
        Text(text = habbit, modifier = Modifier.alpha(0.5f), textDecoration = TextDecoration.LineThrough ))
                Text(text =  "+ $xp HP ", Modifier.padding(start = 25.dp))


            }
        }





    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
var runningDone by remember { mutableStateOf(false ) }// выплолнение бега
        var smokingDone by remember { mutableStateOf(false) } //сигареты
        var showerDone by remember { mutableStateOf(false  ) }// душ
        val runningXp = if (runningDone) 500 else 0
        val smokingXp = if (smokingDone) 25 else 0
        val showerXp = if (showerDone) 10 else 0
//        val totalXp = runningXp + smokingXp + showerXp
        val allDone = runningDone && smokingDone && showerDone
        var totalXp by remember { mutableIntStateOf(0) }
        var dayCompleted by remember { mutableStateOf(false) }
var streak by remember { mutableIntStateOf(0) }
val level = totalXp / 1000
        val newXp = totalXp % 1000
        Column(modifier) {

            Button(
                onClick = {
                    if (allDone && ! dayCompleted){
                        streak++
                        totalXp += showerXp + runningXp + smokingXp
                        dayCompleted = true
                        runningDone = false
                        showerDone = false
                        smokingDone = false

                    }
                }
            ) {
                Text("Завершить день")
            }
            Button(
                onClick = {
                        dayCompleted = false
                }
            ) {
                Text("Начать день")
            }
GreetingSection(name = "Артём", streak = streak, modifier = Modifier.padding(bottom = 5.dp))

            CardLevel(xp = newXp, level = level )


            HabitItem(xp = runningXp, done = runningDone, onToggle = {runningDone = !runningDone}, habbit = "Пробежка")

            HabitItem(xp = smokingXp, done = smokingDone,onToggle = {smokingDone = !smokingDone}, habbit = "Сигареты")
            HabitItem(xp = showerXp, done = showerDone,onToggle = {showerDone = !showerDone}, habbit = "Душ")



        }
    }
}

