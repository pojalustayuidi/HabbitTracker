package com.example.habbittracker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits_table")

data class Habit(
    val name: String,
    val xp:  Int,
    val done: Boolean,
    val savedMoney: Int = 0,
    val habitStartTime: Long = 0L,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)