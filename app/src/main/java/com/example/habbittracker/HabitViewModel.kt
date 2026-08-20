package com.example.habbittracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import model.Habit

class HabitViewModel : ViewModel() {

    var totalxp by mutableStateOf(0)
    private  set
   var habits by mutableStateOf(listOf(
       Habit("Пробежка", 10, false, id = 1),
       Habit("Курение", 20, false, id = 2),
       Habit("Душ", 10, false, id = 3),
   ))
        private set

    fun addBonus(amount: Int){
        totalxp = totalxp + amount
    }
}
// Задача 1. Перенести логику из HomeScreen используя ViewModel
//   var habits by remember { mutableStateOf(
//            listOf(
//                Habit("Пробежка", 10, false, id = 1),
//                Habit("Курение", 20, false, id = 2),
//                Habit("Душ", 10, false, id = 3),
//
//                )
//        ) }

