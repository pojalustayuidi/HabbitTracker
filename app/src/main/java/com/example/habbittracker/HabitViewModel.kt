        package com.example.habbittracker
        import androidx.lifecycle.ViewModel
        import androidx.lifecycle.viewModelScope
        import com.example.habbittracker.data.HabitDao
        import com.example.habbittracker.model.Habit
        import kotlinx.coroutines.flow.MutableStateFlow
        import kotlinx.coroutines.flow.SharingStarted
        import kotlinx.coroutines.flow.StateFlow
        import kotlinx.coroutines.flow.asStateFlow
        import kotlinx.coroutines.flow.stateIn
        import kotlinx.coroutines.launch
        import kotlin.collections.emptyList

        class HabitViewModel(private val habitDao: HabitDao) : ViewModel() {

            val habits: StateFlow<List<Habit>> = habitDao.getAllHabits()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )

            private val _totalXp = MutableStateFlow(0)
                val totalXp: StateFlow<Int> = _totalXp.asStateFlow()

            fun addBonus(amount: Int){
                _totalXp.value += amount
            }

            val totalSavedMoney: StateFlow<Int> = habitDao.getTotalSavedMoney()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = 0
                )
            fun toogleHabit(id: Int) {
                viewModelScope.launch {
                    habitDao.toggleHabitsCompleted(id)
                }
            }
            fun deleteHabit(id: Int){
                viewModelScope.launch {
                    habitDao.deleteHabitById( id  )
                }

            }
            fun addHabit(name: String){

                if(name.isNotBlank()){
                    viewModelScope.launch {
                        val newHabit = Habit(name = name, done = false, xp = 0)
                        habitDao.insertHabit(newHabit)
                    }
                }
            }

            private val _dayCompleted = MutableStateFlow(false)
            val dayCompleted: StateFlow<Boolean> = _dayCompleted.asStateFlow()
            fun completeDay(){
                val allDone = habits.value.all { it.done }
                val allXp = habits.value.filter { it.done }.sumOf {it.xp}
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
viewModelScope.launch {
    habitDao.resetAllHabits()
}
                _dayCompleted.value = false
            }
            private val _streak  = MutableStateFlow(0)
                val streak: StateFlow<Int> =_streak.asStateFlow()




        }

