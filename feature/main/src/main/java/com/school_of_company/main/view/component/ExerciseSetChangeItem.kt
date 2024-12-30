package com.school_of_company.main.view.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun ExerciseSetChangeItem(
    modifier: Modifier = Modifier,
    set: Int,
    weight: Int?,
    count: Int?,
    minute: Int?,
    second: Int?,
    onStateChange: (Int?, Int?, Int?, Int?) -> Unit
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
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            ExerciseTextField(
                                textState = minute.toString(),
                                onTextChange = { newText ->
                                    onStateChange(newText.toIntOrNull(), second, weight, count)
                                }
                            )
                            Text(
                                text = "분",
                                style = typography.bodySmall,
                                color = colors.black,
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            ExerciseTextField(
                                textState = second.toString(),
                                onTextChange = { newText ->
                                    onStateChange(minute, newText.toIntOrNull(), weight, count)
                                }
                            )
                            Text(
                                text = "초",
                                style = typography.bodySmall,
                                color = colors.black
                            )
                        }
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            ExerciseTextField(
                                textState = weight.toString(),
                                onTextChange = { newText ->
                                    onStateChange(minute, second, newText.toIntOrNull(), count)
                                }
                            )
                            Text(
                                text = "kg",
                                style = typography.bodySmall,
                                color = colors.black
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                            ExerciseTextField(
                                textState = count.toString(),
                                onTextChange = { newText ->
                                    onStateChange(minute, second, weight, newText.toIntOrNull())
                                }
                            )
                            Text(
                                text = "회",
                                style = typography.bodySmall,
                                color = colors.black
                            )
                        }
                    }
                }
            }
        }
    }
}