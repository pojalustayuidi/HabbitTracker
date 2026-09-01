package com.example.habbittracker.data.models

enum class HabitType{
    BAD_HABIT, // ВРЕДНАЯ + savedMoney + XP
    GOOD_HABIT // - Полезная + XP
}

data class HabitItemModel(
    val id: Int,
    val type: HabitType,
    val title: String,
    val rewardAmount: Int,

)
