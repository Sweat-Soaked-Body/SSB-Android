package com.school_of_company.main.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBAndroidTheme

data class exercise(
    val weight: Int,
    val time: Int
)

@Composable
internal fun MainTimerExerciseListItem(
    modifier: Modifier = Modifier,
    index: Int,
    item: exercise,
    isCompleted: Boolean
) {
    SSBAndroidTheme { colors, typography ->

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "${index}세트",
                style = typography.bodySmall,
                color = if (isCompleted) colors.black else colors.gray800,
                modifier = Modifier.weight(1f)
            )

            Row(verticalAlignment = Alignment.CenterVertically,) {
                Text(
                    text = "${item.weight}kg",
                    style = typography.bodySmall,
                    color = if (isCompleted) colors.black else colors.gray800,
                )

                Text(
                    text = "X",
                    style = typography.bodySmall,
                    color = if (isCompleted) colors.black else colors.gray800,
                )

                Text(
                    text = "${item.time}회",
                    style = typography.bodySmall,
                    color = if (isCompleted) colors.black else colors.gray800,
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainTimerExerciseListItemPreview() {
    MainTimerExerciseListItem(index = 1, item = exercise(weight = 55, time = 4), isCompleted = false)
}