package com.school_of_company.main.view.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school_of_company.main.util.calendarDateFormat
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.icon.ChevronRightIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.design_system.component.modifier.clickableSingle
import java.util.Calendar

@Composable
fun MainCalendar(
    modifier: Modifier = Modifier,
) {
    val calendar by remember { mutableStateOf(Calendar.getInstance()) }
    var calendarState by remember { mutableStateOf(calendarDateFormat(calendar)) }
    var selectedDayIndex by remember { mutableIntStateOf(calendarState.currentWeekDay - 1) }

    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = colors.white,
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .padding(horizontal = 30.dp)
                .padding(top = 13.dp, bottom = 21.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(48.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChevronLeftIcon(
                    modifier = Modifier
                        .size(18.dp)
                        .clickableSingle {
                            calendar.add(Calendar.WEEK_OF_YEAR, -1)
                            calendarState = calendarDateFormat(calendar)
                        }
                )

                Text(
                    text = "${calendarState.month}월 ${if (calendarState.weekOfYear % 4 == 0) 4 else calendarState.weekOfYear % 4}주",
                    style = typography.bodyMedium,
                    color = colors.black
                )

                ChevronRightIcon(
                    modifier = Modifier
                        .size(18.dp)
                        .clickableSingle {
                            calendar.add(Calendar.WEEK_OF_YEAR, 1)
                            calendarState = calendarDateFormat(calendar)
                        }
                )
            }

            Spacer(modifier = Modifier.height(17.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val days = listOf("일", "월", "화", "수", "목", "금", "토")

                days.forEachIndexed { index, day ->
                    MainCalendarItem(
                        isSelected =  selectedDayIndex == index,
                        day = day,
                        weekDay = calendarState.weekDays[index],
                        modifier = Modifier.clickableSingle {
                            selectedDayIndex = index
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainCalendarPreview() {
    MainCalendar()
}