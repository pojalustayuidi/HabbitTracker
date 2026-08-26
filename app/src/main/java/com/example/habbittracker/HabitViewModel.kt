        package com.example.habbittracker
        import androidx.lifecycle.ViewModel
        import androidx.lifecycle.viewModelScope
        import com.example.habbittracker.data.HabitDao
        import com.example.habbittracker.model.Habit
        import kotlinx.coroutines.flow.MutableStateFlow
        import kotlinx.coroutines.flow.SharingStarted
        import kotlinx.coroutines.flow.StateFlow
        import kotlinx.coroutines.flow.asStateFlow
        import kotlinx.coroutines.flow.filter
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


//            fun toogleHabit(id: Int) {
//                _habits.value = _habits.value.map { habitsFromList ->
//                    if (habitsFromList.id == id) {
//                        habitsFromList.copy(done = !habitsFromList.done)
//                    } else {
//                        habitsFromList
//                    }
//                }
//            }
            fun deleteHabit(id: Int){
                viewModelScope.launch {
                    habitDao.deleteHabitById( id  )
                }

            }
            fun addHabit(name: String){

                if(name.isNotBlank()){
//                    val newId = (_habits.value.maxOfOrNull { it.id } ?: 0) + 1
                    viewModelScope.launch {
                        val newHabit = Habit(name = name, done = false, xp = 0)
                        habitDao.insertHabit(newHabit)
                    }
//                    val newHabit = Habit(name = name, id = newId, done = false, xp =  10)
//                    _habits.value += newHabit
                }
            }

            private val _dayCompleted = MutableStateFlow(false)
            val dayCompleted: StateFlow<Boolean> = _dayCompleted.asStateFlow()
//            fun completeDay(){
//                val allDone = _habits.value.all { it.done }
//                val allXp = _habits.value.filter { it.done }.sumOf {it.xp}
//                if (allDone && !_dayCompleted.value){
//                    _totalXp.value += allXp
//                    _dayCompleted.value = true
//                    _streak.value++
//
//                }
//            }

            fun startNewDay(){
        if (!_dayCompleted.value){
            _streak.value = 0

        }
//                _habits.value = _habits.value.map { clearHabits -> clearHabits.copy(done = false) }
//                _dayCompleted.value = false
            }
            private val _streak  = MutableStateFlow(0)
                val streak: StateFlow<Int> =_streak.asStateFlow()




        }

