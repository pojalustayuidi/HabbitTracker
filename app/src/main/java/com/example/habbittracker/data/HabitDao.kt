package com.example.habbittracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habbittracker.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits_table")
    fun getAllHabits(): Flow<List<Habit>>

    @Insert
    suspend fun insertHabit(habit: Habit)


    @Query("DELETE FROM habits_table WHERE id = :id")
    suspend fun deleteHabitById(id: Int)

    @Query("UPDATE habits_table SET done = NOT done WHERE id = :id")
    suspend fun toggleHabitsCompleted(id: Int)
    @Query("UPDATE habits_table SET done = 0")
    suspend fun resetAllHabits()

    @Query("SELECT COALESCE(SUM(savedMoney), 0) FROM habits_table WHERE done = 1")
    fun getTotalSavedMoney() : Flow <Int>



}