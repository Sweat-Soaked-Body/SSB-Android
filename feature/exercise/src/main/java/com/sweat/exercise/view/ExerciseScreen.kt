package com.sweat.exercise.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.icon.PlusIcon
import com.sweat.design_system.icon.SearchIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.exercise.view.component.ExerciseButton
import com.sweat.exercise.view.component.ExerciseItem

@Composable
fun ExerciseRoute(
    modifier: Modifier = Modifier,
    navigateToAddExerciseScreen: () -> Unit
){
   ExerciseScreen(
       modifier = modifier,
       exerciseItems = listOf()
   )
}

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    exerciseItems: List<Pair<String, Boolean>>
){
    val exerciseList = listOf("전체", "어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타")
    val selectedButton = remember { mutableStateOf(exerciseList.first()) }
    val exerciseStateList = remember { mutableStateListOf<Pair<String, Boolean>>().apply { addAll(exerciseItems) } }
    val isSearching = remember { mutableStateOf(false) }
    val (searchTextState, onSearchTextChange) = remember { mutableStateOf("") }

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
                    vertical = 13.dp,
                    horizontal = 24.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSearching.value) {
                BasicTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = searchTextState,
                    onValueChange = { newText -> onSearchTextChange(newText) },
                    textStyle = SSBTypography.bodySmall.copy(
                        textAlign = TextAlign.Start
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (searchTextState.isEmpty()) {
                                Text(
                                    text = "운동을 적어주세요",
                                    color = SSBColor.gray200,
                                    style = SSBTypography.bodySmall,
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            } else {
                Text(
                    text = "운동",
                    style = SSBTypography.titleSmall
                )
                Spacer(modifier = modifier.weight(1f))
                Row(
                    modifier = modifier
                        .width(72.dp)
                        .height(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        24.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.Top,
                ) {
                    PlusIcon(
                        modifier = modifier
                            .padding(1.dp)
                            .width(24.dp)
                            .height(24.dp)
                            .clickable(onClick = { /*TODO*/ })
                    )
                    SearchIcon(
                        modifier = modifier
                            .padding(1.dp)
                            .width(24.dp)
                            .height(24.dp)
                            .clickable(onClick = { isSearching.value = !isSearching.value })
                    )
                }
            }
        }
        Divider(thickness = 1.dp, color = Color(0xFFEFF0F2))
        Spacer(modifier = modifier.height(10.dp))
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Top,
        ) {
            items(exerciseList){ text ->
                val isSelected = selectedButton.value == text
                ExerciseButton(
                    modifier = modifier,
                    text = text,
                    state = if (isSelected) ButtonState.Disabled else ButtonState.Enabled,
                    onClick = {selectedButton.value = text }
                )
            }
        }
        Spacer(modifier = modifier.height(12.dp))
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 24.dp)
        ) {
            itemsIndexed(exerciseStateList) { index, item ->
                ExerciseItem(
                    modifier = modifier,
                    text = item.first,
                    isSelected = item.second,
                    onHeartClick = {
                        exerciseStateList[index] = item.copy(second = !item.second)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ExercisePreview(){
    ExerciseScreen(
        exerciseItems = listOf(
            "바벨 백스쿼트" to false,
            "바벨 백스쿼트" to false,
            "바벨 백스쿼트" to false,
            "바벨 백스쿼트" to true,
        )
    )
}