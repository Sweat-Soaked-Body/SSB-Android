package com.sweat.exercise.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronSmallDownLightIcon
import com.sweat.design_system.icon.ChevronSmallUpLightIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ExerciseSelector(
    modifier: Modifier,
    text: String,
    items: ImmutableList<String>,
    selectedItem: MutableState<String>,
    expanded: MutableState<Boolean>,
    noItemText: String,
    onItemSelected: (String) -> Unit,
){

    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                style = typography.bodySmall
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                if (expanded.value) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                            .padding(vertical = 5.dp)
                            .background(Color.White)
                    ) {
                        items.forEachIndexed { index, item ->
                            if (index == 0) {
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .clickableSingle {
                                            selectedItem.value = item
                                            onItemSelected(item)
                                            expanded.value = false
                                        }
                                        .padding(horizontal = 16.dp, vertical = 9.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item,
                                        style = typography.bodySmall
                                    )
                                    ChevronSmallUpLightIcon(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clickable { expanded.value = false }
                                    )
                                }
                            } else {
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .clickableSingle {
                                            selectedItem.value = item
                                            expanded.value = false
                                        }
                                        .padding(start = 16.dp, bottom = 9.dp, end = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item,
                                        style = typography.bodySmall
                                    )

                                }
                            }
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .border(
                                    width = 1.dp,
                                    color = colors.gray100,
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 14.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (selectedItem.value.isEmpty()) {
                                Text(
                                    text = noItemText,
                                    color = colors.gray400,
                                    style = typography.bodySmall
                                )
                            } else {
                                Text(
                                    text = selectedItem.value,
                                    style = typography.bodySmall
                                )
                            }
                            ChevronSmallDownLightIcon(
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(24.dp)
                                    .clickable { expanded.value = true }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseSelectorPreview() {
    val selectedItem = remember { mutableStateOf("어깨") }
    val expanded = remember { mutableStateOf(false) }

    ExerciseSelector(
        modifier = Modifier,
        text = "운동종류",
        items = persistentListOf("어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타"),
        selectedItem = selectedItem,
        expanded = expanded,
        noItemText = "",
        onItemSelected = { selectedCategory -> selectedItem.value = selectedCategory }
    )
}