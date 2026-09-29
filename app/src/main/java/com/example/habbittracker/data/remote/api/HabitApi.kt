package com.example.habbittracker.data.remote.api

import com.example.habbittracker.data.remote.dto.HabitRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface HabitApi {

    @POST("api/habits")
    suspend fun saveHabits(
        @Header("Authorization") token: String,
        @Body habits: List<HabitRequest>
    )
}