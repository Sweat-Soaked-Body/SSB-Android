package com.school_of_company.main.util

fun formatTimerText(timerValue: Int): String {
    return String.format(
        "%02d:%02d",
        timerValue / 60,
        timerValue %60
    )
}