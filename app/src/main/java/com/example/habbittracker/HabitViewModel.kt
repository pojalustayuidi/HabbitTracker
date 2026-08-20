package com.example.habbittracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import model.Habit

class HabitViewModel : ViewModel() {

    var totalXp by mutableStateOf(0)
        private set

    fun addBonus(amount: Int){
        totalXp += amount
    }

    var habits by mutableStateOf(
        listOf(
            Habit("Пробежка", 10, false, id = 1),
            Habit("Курение", 20, false, id = 2),
            Habit("Душ", 10, false, id = 3),
        )
    )
        private set

    fun toogleHabit(id: Int) {
        habits = habits.map { habitsFromList ->
            if (habitsFromList.id == id) {
                habitsFromList.copy(done = !habitsFromList.done)
            } else {
                habitsFromList
            }
        }
    }
    fun deleteHabit(id: Int){
        habits = habits.filter{it.id != id}

    }
    fun addHabit(name: String){
        if(name.isNotBlank()){
            val newId = (habits.maxOfOrNull { it.id } ?: 0) + 1
            val newHabit = Habit(name = name, id = newId, done = false, xp =  10)
            habits = habits  + newHabit
        }
    }

    var dayCompleted by mutableStateOf(false)
        private set
    fun completeDay(){
        val allDone = habits.all { it.done }
        val allXp = habits.filter { it.done }.sumOf {it.xp}
        if (allDone && !dayCompleted){
            totalXp += allXp
            dayCompleted = true
            streak++

        }
    }

    fun startNewDay(){
if (!dayCompleted){
    streak = 0

}
        habits = habits.map { clearHabits -> clearHabits.copy(done = false) }
        dayCompleted = false
    }
    var streak by mutableStateOf(0)
        private set




}

