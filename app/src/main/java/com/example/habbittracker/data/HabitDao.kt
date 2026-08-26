package com.example.habbittracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habbittracker.model.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits_table")
    fun getAllHabits():Flow<List<Habit>>

    @Insert
    suspend fun insertHabit(habit: Habit)

    companion object {
        fun getAllHabits() {
            TODO("Not yet implemented")
        }
    }

}