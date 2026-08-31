package com.example.habbittracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.R
import com.example.habbittracker.ui.theme.HabitGreen

@Composable
fun WelcomeScreen(onNavigateToHome: ()-> Unit){
    Column (horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center,

        modifier =  Modifier
            .fillMaxSize()
            .padding(horizontal =4.dp)){

        Text(
                text = "CoinHabit",
        fontSize = 48.sp,
        fontWeight = FontWeight.ExtraBold,
        color = HabitGreen
        )
        Text(modifier = Modifier.padding(32.dp),
            text = "Привычки, которые делают тебя лучше и богаче",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
        )
        Image(
            painter = painterResource(id = R.drawable.ill_jar_with_plant),
            contentDescription = "Банка с сбережениями",
            modifier = Modifier.size(240.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(HabitGreen),
            onClick = onNavigateToHome
        ) { Text("Начать") }
        Text(modifier = Modifier.padding(8.dp),
            text = "Настройка займет около минуты",
            fontSize = 12.sp,
            color = Color.DarkGray,
        )  }
}