package com.sweat.exercise.view

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.icon.PlusIcon
import com.sweat.design_system.icon.SearchIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.exercise.view.component.ExerciseButton
import com.sweat.exercise.view.component.ExerciseItem
import com.sweat.exercise.view.component.ExerciseTextField
import com.sweat.exercise.viewModel.ExerciseIntent
import com.sweat.exercise.viewModel.ExerciseScreenState
import com.sweat.exercise.viewModel.ExerciseViewModel
import com.sweat.ui.DevicePreviews
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ExerciseRoute(
    modifier: Modifier = Modifier,
    viewModel: ExerciseViewModel = hiltViewModel(),
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    ExerciseScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
    )
}

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    state: ExerciseScreenState,
    handleIntent: (ExerciseIntent) -> Unit,
){
    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = modifier
                    .height(57.dp)
                    .padding(
                        vertical = 13.dp,
                        horizontal = 24.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (state.isSearching) {
                    ExerciseTextField(
                        modifier = modifier,
                        text = "운동 이름을 적어주세요",
                        textState = state.searchTextState,
                        onTextChange = { newText ->
                            handleIntent(ExerciseIntent.SetExerciseName(newText))
                        }
                    )
                } else {
                    Text(
                        text = "운동",
                        style = typography.titleSmall,
                        color = colors.black
                    )
                    Box(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = modifier
                                .align(Alignment.CenterEnd),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PlusIcon(
                                modifier = modifier
                                    .size(24.dp)
                                    .clickable(onClick = { handleIntent(ExerciseIntent.AddExercise) })
                            )
                            SearchIcon(
                                modifier = modifier
                                    .size(24.dp)
                                    .clickable(onClick = { handleIntent(ExerciseIntent.ToggleSearchMode) })
                            )
                        }
                    }
                }
            }
            Divider(thickness = 1.dp, color = colors.gray100)
            Spacer(modifier = modifier.height(10.dp))
            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.Top,
            ) {
                items(state.exerciseList) { text ->
                    val isSelected = state.selectedButton == text
                    ExerciseButton(
                        modifier = modifier,
                        text = text,
                        state = if (isSelected) ButtonState.Disabled else ButtonState.Enabled,
                        onClick = { handleIntent(ExerciseIntent.SetExerciseCategory(text)) }
                    )
                }
            }
            Spacer(modifier = modifier.height(12.dp))
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                itemsIndexed(state.exerciseStateList) { index, item ->
                    ExerciseItem(
                        modifier = modifier,
                        text = item.first,
                        isSelected = item.second,
                        onHeartClick = {
                            val updatedList = state.exerciseStateList.toMutableList()
                            updatedList[index] = item.copy(second = !item.second)
                            handleIntent(ExerciseIntent.UpdateExerciseItems(updatedList.toImmutableList()))
                        }
                    )
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun ExercisePreview() {
    ExerciseScreen(
        modifier = Modifier,
        state = ExerciseScreenState.getInitialState(),
        handleIntent = { _ -> },
    )
}