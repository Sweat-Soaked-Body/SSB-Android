package com.sweat.exercise.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.icon.ChevronSmallDownLightIcon
import com.sweat.design_system.icon.ChevronSmallUpLightIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun AddExerciseScreen(
    modifier: Modifier = Modifier,
){
    val categories = listOf("어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타")
    val exerciseTypeExpanded = remember { mutableStateOf(false) }
    val selectedCategory = remember { mutableStateOf("어깨") }
    val (exerciseTextState, onExerciseTextChange) = remember { mutableStateOf("") }
    val exerciseStyleExpanded = remember { mutableStateOf(false) }
    val exerciseStyle = listOf("시간", "세트")
    val selectedStyle = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(57.dp)
                .padding(
                    vertical = 15.dp,
                    horizontal = 24.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ChevronLeftIcon(
                modifier = modifier
                    .padding(1.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable(onClick = { /*TODO*/ })
            )
            Text(
                text = "운동 추가",
                style = SSBTypography.subTitle
            )
            Spacer(modifier = modifier.width(24.dp))
        }
        Divider(thickness = 1.dp, color = Color(0xFFEFF0F2))
        Spacer(modifier = modifier.height(24.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "운동 종류",
                style = SSBTypography.bodySmall
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                if (exerciseTypeExpanded.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                            .padding(vertical = 5.dp)
                            .background(Color.White)
                    ) {
                        categories.forEachIndexed { index, category ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedCategory.value = category
                                        exerciseTypeExpanded.value = false
                                    }
                                    .padding(horizontal = 16.dp, vertical = 9.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = category,
                                    style = SSBTypography.bodySmall
                                )
                                if (index == 0) {
                                    ChevronSmallUpLightIcon(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp)
                                            .clickable { exerciseTypeExpanded.value = false }
                                    )
                                }
                            }
                        }
                    }
                }
                else{
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFEFF0F2),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(start = 16.dp, top = 14.dp, end = 16.dp, bottom = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedCategory.value,
                            style = SSBTypography.bodySmall
                        )
                        ChevronSmallDownLightIcon(
                            modifier = modifier
                                .width(24.dp)
                                .height(24.dp)
                                .clickable { exerciseTypeExpanded.value = true }
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "운동 이름",
                style = SSBTypography.bodySmall
            )
            Row(
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFEFF0F2),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(start = 16.dp, top = 14.dp, end = 149.dp, bottom = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = exerciseTextState,
                    onValueChange = { newText -> onExerciseTextChange(newText) },
                    textStyle = SSBTypography.bodySmall.copy(
                        textAlign = TextAlign.Start
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (exerciseTextState.isEmpty()) {
                                Text(
                                    text = "운동 이름을 적어주세요",
                                    color = SSBColor.gray400,
                                    style = SSBTypography.bodySmall,
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "시간으로 운동할까요? 세트로 운동할까요?",
                style = SSBTypography.bodySmall
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                if (exerciseStyleExpanded.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                            .padding(vertical = 5.dp)
                            .background(Color.White)
                    ) {
                        exerciseStyle.forEachIndexed { index, exerciseStyle ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedStyle.value = exerciseStyle
                                        exerciseStyleExpanded.value = false
                                    }
                                    .padding(horizontal = 16.dp, vertical = 9.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = exerciseStyle,
                                    style = SSBTypography.bodySmall
                                )
                                if (index == 0) {
                                    ChevronSmallUpLightIcon(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp)
                                            .clickable { exerciseStyleExpanded.value = false }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFEFF0F2),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(start = 16.dp, top = 14.dp, end = 16.dp, bottom = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selectedStyle.value.isEmpty()) {
                            Text(
                                text = "시간/세트 선택해주세요",
                                color = SSBColor.gray400,
                                style = SSBTypography.bodySmall
                            )
                        }
                        else{
                            Text(
                                text = selectedStyle.value,
                                style = SSBTypography.bodySmall
                            )
                        }
                        ChevronSmallDownLightIcon(
                            modifier = modifier
                                .width(24.dp)
                                .height(24.dp)
                                .clickable {
                                    exerciseStyleExpanded.value = true
                                }
                        )
                    }
                }
            }
        }
        Spacer(modifier = modifier.weight(1f))
        SSBButton(
            modifier = modifier.fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            text = "추가",
            state = if (exerciseTextState.isNotEmpty() && selectedStyle.value.isNotEmpty()) ButtonState.Enabled
            else ButtonState.Disabled,
            onClick = { /*TODO*/ }
        )
    }
}

@Preview
@Composable
fun AddExercisePreivew(){
    AddExerciseScreen()
}