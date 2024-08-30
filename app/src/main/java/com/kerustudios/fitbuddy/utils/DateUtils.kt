package com.kerustudios.fitbuddy.utils

import android.icu.util.Calendar
import java.util.Locale

fun getToday(): Triple<String, String, String> {
    val date = Calendar.getInstance(Locale.getDefault()).time
    val month = Calendar.MONTH.getMonth()
    val dayOfMonth = Calendar.DAY_OF_MONTH.toString()
    val dayOfWeek = Calendar.DAY_OF_WEEK.getDayOfWeek()

    return Triple(dayOfMonth, dayOfWeek, month)
}

fun Int.getMonth(): String {
    return when (this) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid month"
    }
}

fun Int.getDayOfWeek(): String {
    return when (this) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Invalid day of week"
    }
}