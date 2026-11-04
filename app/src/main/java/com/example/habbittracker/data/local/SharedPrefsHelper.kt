package com.example.habbittracker.data.local

import android.content.Context

class SharedPrefsHelper (context: Context){

val prefs = context.getSharedPreferences("coinhabit_prefs", Context.MODE_PRIVATE)

    fun saveStartTime(number: Long){
        prefs.edit().putLong("MY_START_TIME_KEY", number).apply()
    }
    fun getStartTime(): Long{
        return prefs.getLong("MY_START_TIME_KEY", 0L)
    }
    fun saveOnboardingCompleted(isBoarding: Boolean) {
        prefs.edit().putBoolean("ONBOARDING_COMPLETED_KEY", isBoarding).apply()
    }
    fun isOnboardingCompleted() : Boolean
    {
        return prefs.getBoolean("ONBOARDING_COMPLETED_KEY", false)
    }
}