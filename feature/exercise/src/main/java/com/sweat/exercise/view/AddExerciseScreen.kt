package com.sweat.exercise.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.exercise.view.component.AddExerciseSelector
import com.sweat.exercise.view.component.AddExerciseTextField
import com.sweat.exercise.viewModel.AddExerciseIntent
import com.sweat.exercise.viewModel.AddExerciseScreenState
import com.sweat.exercise.viewModel.AddExerciseViewModel
import com.sweat.model.param.exercise.ExerciseAddRequestParam
import com.sweat.ui.DevicePreviews

@Composable
internal fun AddExerciseRoute(
    viewModel: AddExerciseViewModel = hiltViewModel(),
    popUpBackStack: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddExerciseScreen(
        modifier = Modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
        popUpBackStack = popUpBackStack
    )
}

@Composable
private fun AddExerciseScreen(
    modifier: Modifier = Modifier,
    state: AddExerciseScreenState,
    handleIntent: (AddExerciseIntent) -> Unit,
    popUpBackStack: () -> Unit
) {
    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .statusBarsPadding()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ChevronLeftIcon(
                    modifier = modifier
                        .padding(1.dp)
                        .size(24.dp)
                        .clickableSingle { popUpBackStack() }
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
                AddExerciseSelector(
                    modifier = modifier,
                    text = "운동종류",
                    items = state.categories,
                    selectedItem = remember { mutableStateOf(state.categories[state.selectedCategory]) },
                    expanded = remember { mutableStateOf(state.exerciseTypeExpanded) },
                    noItemText = "",
                    onItemSelected = { selectedCategory ->
                        handleIntent(AddExerciseIntent.ExerciseCategory(state.categories.indexOf(selectedCategory)))
                    }
                )
                Spacer(modifier = modifier.height(2.dp))
                AddExerciseTextField(
                    modifier = modifier,
                    text = "운동 이름",
                    textState = state.textState,
                    placeHolder = "운동 이름을 적어주세요",
                    onTextChange = { newText ->
                        handleIntent(AddExerciseIntent.ExerciseName(newText))
                    }
                )
            }
            Spacer(modifier = modifier.weight(1f))
            SSBButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                text = "추가",
                state = if (state.textState.isNotEmpty()) ButtonState.Enabled
                else ButtonState.Disabled,
                onClick = {
                    val exerciseAddRequest = ExerciseAddRequestParam(
                        name = state.textState,
                        category = state.selectedCategory + 1
                    )
                    handleIntent(AddExerciseIntent.AddExercise(exerciseAddRequest))
                    popUpBackStack()
                }
            )
        }
    }
}

@DevicePreviews
@Composable
fun AddExerciseScreenPreview() {
    AddExerciseScreen(
        state = AddExerciseScreenState.getInitialState(),
        handleIntent = { _ ->},
        popUpBackStack = {}
    )
}
