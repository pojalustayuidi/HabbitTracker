package com.example.habbittracker.data.repository

import com.example.habbittracker.data.local.HabitDao
import com.example.habbittracker.data.local.SharedPrefsHelper
import com.example.habbittracker.data.models.Habit
import kotlinx.coroutines.flow.Flow

class HabitRepository (private val habitDao: HabitDao, private val prefsHelper: SharedPrefsHelper){
    val allHabits: Flow<List<Habit>> = habitDao.getAllHabits()
    val totalSavedMoney: Flow<Int> = habitDao.getTotalSavedMoney()

        suspend fun insertHabit(habit: Habit){habitDao.insertHabit(habit) }

    suspend fun toggleHabitsCompleted(id: Int){
        habitDao.toggleHabitsCompleted(id)
    }
    suspend fun deleteHabitById(id: Int){
        habitDao.deleteHabitById(id)
    }
    suspend fun resetAllHabits(){
        habitDao.resetAllHabits()
    }
    fun saveStartTime(time: Long){
        prefsHelper.saveStartTime(number = time)
    }
    fun getStartTime(): Long {
       return prefsHelper.getStartTime()
    }


    suspend fun updateStartTime(id: Int, time: Long){
        habitDao.updateStartTime(id, time)
    }
}