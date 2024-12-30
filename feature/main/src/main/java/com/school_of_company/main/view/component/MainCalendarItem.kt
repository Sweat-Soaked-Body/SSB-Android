package com.school_of_company.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun MainCalendarItem (
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    day: String,
    weekDay: String
){
    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .background(color = if(isSelected) colors.gray800 else colors.white, shape = RoundedCornerShape(size = 6.dp))
                .padding(horizontal = 10.dp, vertical = 2.dp)
                .width(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = weekDay,
                style = typography.bodySmall,
                color =if (isSelected) {
                    colors.gray300
                } else {
                    when (day) {
                        "일" -> colors.error.copy(alpha = 0.5f)
                        "토" -> colors.blue.copy(alpha = 0.5f)
                        else -> colors.gray300
                    }
                }
            )

            Text(
                text = day,
                style = typography.bodySmall,
                color = if (isSelected) {
                    colors.white
                } else {
                    when (day) {
                        "일" -> colors.error
                        "토" -> colors.blue
                        else -> colors.black
                    }
                }
            )
        }
    }
}