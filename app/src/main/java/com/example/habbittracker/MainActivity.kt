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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun CardLevel(level: Int, xp: Int, ){
    Column(Modifier.background(color = Color.Gray)) {
        Text(text = "Уровень $level")

        Text(text = "$xp / 1000 XP")
    }}


        @Composable
        fun HabitItem(habbit: String, xp: Int){
            var done by remember { mutableStateOf(false) }
            //// remember — сохраняем, mutableStateOf — состояние, by — работаем с ним как с обычной переменной
            Row(
                modifier =  Modifier
                    .background(Color.Red)
                    .padding(5.dp)
                    .fillMaxWidth()
                .clickable()
                {
                done = !done //«Возьми текущее значение done, переверни его и запиши обратно в done».
            }) {
                Checkbox(checked = done, onCheckedChange = null) //false → true
if (done == false){
    Text(text = habbit, )
}
                else (
        Text(text = habbit, modifier = Modifier.alpha(0.5f), textDecoration = TextDecoration.LineThrough ))
                Text(text =  "+ $xp HP ", Modifier.padding(start = 25.dp))


            }
        }





    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {

        Column(modifier) {

            GreetingSection(name = "Артём", streak = 10, modifier = Modifier.padding(bottom = 5.dp))
            CardLevel(xp = 100, level = 2)
            HabitItem(xp = 10, habbit = "Пробежка")
            HabitItem(xp = 25, habbit = "Сигареты")
            HabitItem(xp = 10, habbit = "Душ")



        }
    }
}

