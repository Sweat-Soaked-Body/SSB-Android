package com.school_of_company.main.util

import android.util.Log
import com.school_of_company.main.viewmodel.CalendarState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun calendarDateFormat(calendar: Calendar): CalendarState {
    // 주의 시작일 계산
    calendar.firstDayOfWeek = Calendar.SUNDAY
    val dayOfWeek = calendar[Calendar.DAY_OF_WEEK] - calendar.firstDayOfWeek
    calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek)

    // 주간 날짜 계산
    val weekDays = mutableListOf<String>()
    var nextMonth = false
    for (i in 0..6) {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        weekDays.add(day.toString())
        if (day == 1 && i != 0) {
            nextMonth = true
        }
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    // 달의 값을 포맷 (calendar를 조작한 후 다시 설정)
    calendar.add(Calendar.DAY_OF_MONTH, -7)
    val sfMonth = SimpleDateFormat("MM", Locale.KOREA)
    var month = sfMonth.format(calendar.time)

    if (nextMonth) {
        calendar.add(Calendar.MONTH, 1)
        month = sfMonth.format(calendar.time)
        calendar.add(Calendar.MONTH, -1)
    }

    // 주차 계산
    val weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR)

    //요일 계산
    val currentWeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)


    return CalendarState(
        weekOfYear = weekOfYear,
        month = month,
        weekDays = weekDays,
        currentWeekDay = currentWeekDay
    )
}
