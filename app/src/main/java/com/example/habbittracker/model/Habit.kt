package com.example.habbittracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "habits_table")

data class Habit(
    val name: String,
    val xp:  Int,
    val done: Boolean,
    val savedMoney: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)


