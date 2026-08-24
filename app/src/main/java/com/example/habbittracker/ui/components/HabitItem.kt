package com.example.habbittracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.model.Habit
import com.example.habbittracker.ui.theme.HabitTextSecondary

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
                Column() {Text(text = habit.name)
                    Text(text = "5/7 дней", color = HabitTextSecondary, fontSize = 12.sp)
                }

            }
            else {
                Text(text = habit.name, modifier = Modifier.alpha(0.5f), textDecoration = TextDecoration.LineThrough )
                Text(text =  "+ ${habit.xp} HP ", Modifier.padding(start = 25.dp))}
            IconButton(
                onClick = {
//будет редактириование
                },
            ){
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Удалить привычку")
            }



        }

    }

}