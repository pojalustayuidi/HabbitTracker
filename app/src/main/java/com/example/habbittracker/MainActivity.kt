            package com.example.habbittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           HomeScreen()

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
        fun HabitItem(checkbox: Char, habbit: String, xp: Int){
            Row(Modifier.background(color = Color.Red)) {
                Text(text =  "$checkbox", Modifier.padding(horizontal = 10.dp))
                Text(text = habbit)
                Text(text = "+$xp XP") }}


    @Composable
    fun HomeScreen() {
        Column {
            Spacer(modifier = Modifier.height(100.dp))

            GreetingSection(name = "Артём", streak = 10, modifier = Modifier.padding(bottom = 5.dp))
            Spacer(modifier = Modifier.height(100.dp))
            CardLevel(xp = 100, level = 2)
            Spacer(modifier = Modifier.height(100.dp))
            HabitItem(xp = 10, checkbox = '☐', habbit = "Пробежка")
            HabitItem(xp = 25, checkbox = '☐', habbit = "Сигареты")
            HabitItem(xp = 10, checkbox = '☐', habbit = "Душ")



        }
    }
}

