package com.sweat.exercise.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.modifier.clickableSingle
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

@Composable
fun ExerciseRoute(
    viewModel: ExerciseViewModel = hiltViewModel(),
    navigateToAddExercise: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.loadExercises(id = 1)
    }

    ExerciseScreen(
        modifier = Modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
        navigateToAddExercise = navigateToAddExercise
    )
}

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    state: ExerciseScreenState,
    handleIntent: (ExerciseIntent) -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .statusBarsPadding()
        ) {
            Row(
                modifier = modifier
                    .padding(vertical = 13.dp, horizontal = 24.dp),
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
                            modifier = modifier.align(Alignment.CenterEnd),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PlusIcon(
                                modifier = modifier
                                    .size(24.dp)
                                    .clickableSingle { navigateToAddExercise() }
                            )
                            SearchIcon(
                                modifier = modifier
                                    .size(24.dp)
                                    .clickableSingle(onClick = { handleIntent(ExerciseIntent.ToggleSearchMode) })
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
                    val category = mapCategoryToInt(text)
                    val isSelected = state.selectedButton == category
                    ExerciseButton(
                        modifier = modifier,
                        text = text,
                        state = if (isSelected) ButtonState.Disabled else ButtonState.Enabled,
                        onClick = { handleIntent(ExerciseIntent.SetExerciseCategory(category)) }
                    )
                }
            }
            Spacer(modifier = modifier.height(12.dp))

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { handleIntent(ExerciseIntent.UpdateExerciseItems(state.exerciseStateList)) },
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        contentColor = colors.main
                    )
                }
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    val itemsToDisplay = state.filteredExerciseStateList

                    if (itemsToDisplay.isNotEmpty()) {
                        items(itemsToDisplay) { item ->
                            ExerciseItem(
                                modifier = modifier,
                                text = item.name,
                                isSelected = item.like,
                                onHeartClick = {
                                    handleIntent(
                                        ExerciseIntent.ToggleLikeStatus(
                                            item.id,
                                            item.like
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun mapCategoryToInt(category: String): Int {
    return when (category) {
        "전체" -> -1
        "어깨" -> 1
        "등" -> 2
        "가슴" -> 3
        "하체" -> 4
        "팔" -> 5
        "역도" -> 6
        "복근" -> 7
        "유산소" -> 8
        "기타" -> 9
        else -> -1
    }
}

@DevicePreviews
@Composable
fun ExercisePreview() {
    ExerciseScreen(
        modifier = Modifier,
        state = ExerciseScreenState.getInitialState(),
        handleIntent = { _ -> },
        navigateToAddExercise = {}
    )
}
