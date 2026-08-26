package com.example.habbittracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habbittracker.model.Habit

@Database(entities = [Habit:: class], version = 1)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao
}