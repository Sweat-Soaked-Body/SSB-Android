package com.school_of_company.main.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.icon.MultiplyImage
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun ExerciseSetItem(
    modifier: Modifier = Modifier,
    set: Int,
    weight: Int?,
    count:Int?,
    minute: Int?,
    second: Int?
) {
    SSBAndroidTheme { colors, typography ->
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${set}세트",
                style = typography.bodySmall,
                color = colors.black
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                if(weight == null) {
                    Text(
                        text = "${minute}분",
                        style = typography.bodySmall,
                        color = colors.black
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${second}초",
                        style = typography.bodySmall,
                        color = colors.black
                    )
                } else {
                    Text(
                        text = "${weight}kg",
                        style = typography.bodySmall,
                        color = colors.black
                    )

                    MultiplyImage(modifier = Modifier.size(20.dp))

                    Text(
                        text = "${count}회",
                        style = typography.bodySmall,
                        color = colors.black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseSetItemPreview() {
    ExerciseSetItem(
        set = 1,
        weight = 555,
        count = 55,
        minute = null,
        second = null
    )
}
