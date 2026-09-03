package com.example.habbittracker.ui.state

import com.example.habbittracker.data.models.Habit

data class HomeUiState(
    val habits: List<Habit>,
    val totalXp: Int,
    val streak: Int
)