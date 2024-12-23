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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.exercise.view.component.AddExerciseSelector
import com.sweat.exercise.view.component.AddExerciseTextField
import com.sweat.exercise.viewModel.AddExerciseIntent
import com.sweat.exercise.viewModel.AddExerciseScreenSideEffect
import com.sweat.exercise.viewModel.AddExerciseScreenState
import com.sweat.exercise.viewModel.AddExerciseViewModel
import com.sweat.ui.DevicePreviews
import kotlinx.collections.immutable.persistentListOf

@Composable
fun AddExerciseRoute(
    modifier: Modifier = Modifier,
    viewModel: AddExerciseViewModel = hiltViewModel(),
    navigateToExercise: () -> Unit,
    popUpBackStack: () -> Unit,
){
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
            viewModel.sideEffect.collect { sideEffect ->
                when(sideEffect) {
                    AddExerciseScreenSideEffect.NavigateToExercise -> navigateToExercise()
                    AddExerciseScreenSideEffect.PopUpBackStack -> popUpBackStack()
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    AddExerciseScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
    )
}

@Composable
fun AddExerciseScreen(
    modifier: Modifier = Modifier,
    state: AddExerciseScreenState,
    handleIntent: (AddExerciseIntent) -> Unit,
) {
    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
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
                        .clickableSingle{ handleIntent(AddExerciseIntent.PopUpBackStack) }
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
                    items = persistentListOf("어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타"),
                    selectedItem = remember { mutableStateOf(state.selectedCategory) },
                    expanded = remember { mutableStateOf(state.exerciseTypeExpanded) },
                    noItemText = "",
                    onItemSelected = { selectedCategory ->
                        handleIntent(AddExerciseIntent.ExerciseCategory(selectedCategory))
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
                Spacer(modifier = modifier.height(2.dp))
                AddExerciseSelector(
                    modifier = modifier,
                    text = "시간으로 운동할까요? 세트로 운동할까요?",
                    items = persistentListOf("시간", "세트"),
                    selectedItem = remember { mutableStateOf(state.selectedStyle) },
                    expanded = remember { mutableStateOf(state.exerciseStyleExpanded) },
                    noItemText = "시간/세트 선택해주세요",
                    onItemSelected = { selectedStyle ->
                        handleIntent(AddExerciseIntent.ExerciseStyle(selectedStyle))
                    }
                )
            }
            Spacer(modifier = modifier.weight(1f))
            SSBButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                text = "추가",
                state = if (state.textState.isNotEmpty() && state.selectedStyle.isNotEmpty()) ButtonState.Enabled
                else ButtonState.Disabled,
                onClick = { /*TODO*/ }
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
    )
}
