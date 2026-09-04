package com.example.habbittracker.ui.components.helpers


fun formatElapsedTime(totalSeconds: Int) : String{

    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)

}