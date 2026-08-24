package com.example.habbittracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.habbittracker.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HabitViewModel : ViewModel() {

    private val _totalXp = MutableStateFlow(0)
        val totalXp: StateFlow<Int> = _totalXp.asStateFlow()

    fun addBonus(amount: Int){
        _totalXp.value += amount
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

    private val _dayCompleted = MutableStateFlow(false)
    val dayCompleted: StateFlow<Boolean> = _dayCompleted.asStateFlow()
    fun completeDay(){
        val allDone = habits.all { it.done }
        val allXp = habits.filter { it.done }.sumOf {it.xp}
        if (allDone && !_dayCompleted.value){
            _totalXp.value += allXp
            _dayCompleted.value = true
            _streak.value++

        }
    }

    fun startNewDay(){
if (!_dayCompleted.value){
    _streak.value = 0

}
        habits = habits.map { clearHabits -> clearHabits.copy(done = false) }
        _dayCompleted.value = false
    }
    private val _streak  = MutableStateFlow(0)
        val streak: StateFlow<Int> =_streak.asStateFlow()




}

