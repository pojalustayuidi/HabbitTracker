    package com.example.habbittracker
    import androidx.lifecycle.ViewModel
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

        private val _habits = MutableStateFlow<List<Habit>>(listOf(
            Habit("Пробежка", 10, false, id = 1),
            Habit("Курение", 20, false, id = 2),
            Habit("Душ", 10, false, id = 3),
        ))
        val habits: StateFlow<List<Habit>> = _habits.asStateFlow()
        fun toogleHabit(id: Int) {
            _habits.value = _habits.value.map { habitsFromList ->
                if (habitsFromList.id == id) {
                    habitsFromList.copy(done = !habitsFromList.done)
                } else {
                    habitsFromList
                }
            }
        }
        fun deleteHabit(id: Int){
            _habits.value = _habits.value.filter{it.id != id}

        }
        fun addHabit(name: String){
            if(name.isNotBlank()){
                val newId = (_habits.value.maxOfOrNull { it.id } ?: 0) + 1
                val newHabit = Habit(name = name, id = newId, done = false, xp =  10)
                _habits.value += newHabit
            }
        }

        private val _dayCompleted = MutableStateFlow(false)
        val dayCompleted: StateFlow<Boolean> = _dayCompleted.asStateFlow()
        fun completeDay(){
            val allDone = _habits.value.all { it.done }
            val allXp = _habits.value.filter { it.done }.sumOf {it.xp}
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
            _habits.value = _habits.value.map { clearHabits -> clearHabits.copy(done = false) }
            _dayCompleted.value = false
        }
        private val _streak  = MutableStateFlow(0)
            val streak: StateFlow<Int> =_streak.asStateFlow()




    }

