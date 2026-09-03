package com.example.habbittracker.data

import com.example.habbittracker.data.models.HabitItemModel
import com.example.habbittracker.data.models.HabitType

object HabitPresets{
    val defaultHabits = listOf(
        HabitItemModel(
            id = 1,
            type = HabitType.BAD_HABIT,
            title = "Сигареты",
            rewardAmount = 1,
        ),
        HabitItemModel(
            id = 2,
            type = HabitType.BAD_HABIT,
            title = "Энергетики",
            rewardAmount = 1,
        ),
        HabitItemModel(
            id = 3,
            type = HabitType.BAD_HABIT,
            title = "Вейп",
            rewardAmount = 1,
        ),
        HabitItemModel(
            id = 4,
            type = HabitType.BAD_HABIT,
            title = "Алкоголь",
            rewardAmount = 1,
        ),
        HabitItemModel(
            id = 5,
            type = HabitType.GOOD_HABIT,
            title = "Чтение книг",
            rewardAmount = 2,
        ),
        HabitItemModel(
            id = 6,
            type = HabitType.GOOD_HABIT,
            title = "Пить воду",
            rewardAmount = 2,
        )

    )
}
