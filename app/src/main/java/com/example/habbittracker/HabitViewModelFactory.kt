package com.example.habbittracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habbittracker.data.HabitDao

class HabitViewModelFactory(private val habitDao: HabitDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(habitDao) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }
}