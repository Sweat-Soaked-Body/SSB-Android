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
import androidx.compose.foundation.layout.size
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
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.icon.ChevronSmallDownLightIcon
import com.sweat.design_system.icon.ChevronSmallUpLightIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.design_system.theme.SSBTypography
import com.sweat.exercise.view.component.ExerciseSelector
import com.sweat.exercise.view.component.ExerciseTextField
import kotlinx.collections.immutable.ImmutableList

@Composable
fun AddExerciseScreen(
    modifier: Modifier = Modifier,
){
    val categories = listOf("어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타")
    val exerciseTypeExpanded = remember { mutableStateOf(false) }
    val selectedCategory = remember { mutableStateOf("어깨") }
    val (textState, onTextChange) = remember { mutableStateOf("") }
    val exerciseStyleExpanded = remember { mutableStateOf(false) }
    val exerciseStyle = listOf("시간", "세트")
    val selectedStyle = remember { mutableStateOf("") }

    SSBAndroidTheme { colors, typography ->
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
                        .size(24.dp)
                        .clickableSingle(onClick = { /*TODO*/ })
                )
                Text(
                    text = "운동 추가",
                    style = typography.subTitle,
                    color = colors.black
                )
                Spacer(modifier = modifier.width(24.dp))
            }
            Divider(thickness = 1.dp, color = colors.gray100)
            Spacer(modifier = modifier.height(2.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                ExerciseSelector(
                    modifier = modifier,
                    text = "운동종류",
                    items = listOf("어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타"),
                    selectedItem = selectedCategory,
                    expanded = exerciseTypeExpanded,
                    noItemText = "",
                )
                Spacer(modifier = modifier.height(2.dp))
                ExerciseTextField(
                    modifier = modifier,
                    text = "운동 이름",
                    textState = textState,
                    placeHolder = "운동 이름을 적어주세요",
                    onTextChange = onTextChange,
                )
                Spacer(modifier = modifier.height(2.dp))
                ExerciseSelector(
                    modifier = modifier,
                    text = "시간으로 운동할까요? 세트로 운동할까요?",
                    items = listOf("시간", "세트"),
                    selectedItem = selectedStyle,
                    expanded = exerciseStyleExpanded,
                    noItemText = "시간/세트 선택해주세요"
                )
            }
            Spacer(modifier = modifier.weight(1f))
            SSBButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                text = "추가",
                state = if (textState.isNotEmpty() && selectedStyle.value.isNotEmpty()) ButtonState.Enabled
                else ButtonState.Disabled,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Preview
@Composable
fun AddExercisePreivew(){
    AddExerciseScreen()
}