package com.example.habbittracker.data

import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.habbittracker.model.Habit

@Database(entities = [Habit:: class], version = 1)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object{
        @Volatile
        private  var INSTANCE: HabitDatabase? = null
        fun getDatabase(context: android.content.Context): HabitDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }


    }
}