package com.example.habbittracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbittracker.data.HabitPresets
import com.example.habbittracker.data.models.Habit
import com.example.habbittracker.data.repository.HabitRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HabitViewModel(private val repository: HabitRepository) : ViewModel() {

    val habits: StateFlow<List<Habit>> = repository.allHabits.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
private val _selectedHabitsIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedHabitsIds: StateFlow<Set<Int>> = _selectedHabitsIds.asStateFlow()
    fun toggleHabitSelection(id: Int){
      val currentSet =  selectedHabitsIds.value
        _selectedHabitsIds.value = if (currentSet.contains(id)){
             currentSet -  id
        }else {
            currentSet + id
        }

    }


    private val _totalXp = MutableStateFlow(0)
    val totalXp: StateFlow<Int> = _totalXp.asStateFlow()

    private val _hoursPassed = MutableStateFlow(0L)
    val hoursPassed: StateFlow<Long> = _hoursPassed.asStateFlow()
    fun calculateHoursPassed(){
viewModelScope.launch {
    if(repository.getStartTime() == 0L)
    {
        _hoursPassed.value = 0L
    }else {
       _hoursPassed.value = (System.currentTimeMillis() - repository.getStartTime()) /  1000
    }
}
    }

    fun startTimeTracking(){
        if (repository.getStartTime() == 0L){
            repository.saveStartTime(System.currentTimeMillis())
            calculateHoursPassed()
        }
    }

    fun addBonus(amount: Int) {
        _totalXp.value += amount
    }


    private val _currentTime = MutableStateFlow(System.currentTimeMillis())
    val currentTime: StateFlow<Long> = _currentTime.asStateFlow()

    fun startTicking() {
        viewModelScope.launch {
            while (true) {
                delay(1000.milliseconds)
                _currentTime.value = System.currentTimeMillis()
            }
        }
    }
    fun saveConfiguredHabits(coast: Map<Int, String>) {
        viewModelScope.launch {
             coast.forEach { (id, costString) -> val preset = HabitPresets.defaultHabits.find {it.id == id}

                if (preset != null){
                    val savedMoney = costString.toIntOrNull() ?: 0
                    val newHabit = Habit(name = preset.title, done = false, xp = 0, savedMoney = savedMoney, habitStartTime = System.currentTimeMillis())
                    repository.insertHabit(newHabit)

                }
            }

        }
    }


    val totalSavedMoney: StateFlow<Int> = repository.totalSavedMoney
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun toggleHabit(id: Int) {
        viewModelScope.launch {
            repository.toggleHabitsCompleted(id)
        }
    }

    fun deleteHabit(id: Int) {
        viewModelScope.launch {
            repository.deleteHabitById(id)
        }

    }

    fun addHabit(name: String) {

        if (name.isNotBlank()) {
            viewModelScope.launch {
                val newHabit = Habit(name = name, done = false, xp = 0, habitStartTime = System.currentTimeMillis())
                repository.insertHabit(newHabit)
            }
        }
    }

    private val _dayCompleted = MutableStateFlow(false)
    val dayCompleted: StateFlow<Boolean> = _dayCompleted.asStateFlow()
    fun completeDay() {
        val allDone = habits.value.all { it.done }
        val allXp = habits.value.filter { it.done }.sumOf { it.xp }
        if (allDone && !_dayCompleted.value) {
            _totalXp.value += allXp
            _dayCompleted.value = true
            _streak.value++

        }
    }

    fun startNewDay() {
        if (!_dayCompleted.value) {
            _streak.value = 0

        }
        viewModelScope.launch {
            repository.resetAllHabits()
        }
        _dayCompleted.value = false
    }

    private val _streak = MutableStateFlow(0)
    val streak: StateFlow<Int> = _streak.asStateFlow()


}