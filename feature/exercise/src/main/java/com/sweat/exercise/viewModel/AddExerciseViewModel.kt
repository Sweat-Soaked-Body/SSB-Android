package com.sweat.exercise.viewModel

import com.sweat.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor() :
    BaseViewModel<AddExerciseScreenState, AddExerciseScreenSideEffect, AddExerciseIntent>(
        AddExerciseScreenState.getInitialState()
    ) {
    override fun handleIntent(intent: AddExerciseIntent) {
        when (intent) {
            is AddExerciseIntent.SetExerciseName -> setState { copy(textState = intent.state) }
            is AddExerciseIntent.SetExerciseCategory -> setState { copy(selectedCategory = intent.category) }
            is AddExerciseIntent.SetExerciseStyle -> setState { copy(selectedStyle = intent.style) }
            is AddExerciseIntent.ToggleExerciseTypeDropdown -> setState { copy(exerciseTypeExpanded = !exerciseTypeExpanded) }
            is AddExerciseIntent.ToggleExerciseStyleDropdown -> setState { copy(exerciseStyleExpanded = !exerciseStyleExpanded) }
        }
    }
}

data class AddExerciseScreenState(
    val exerciseTypeExpanded: Boolean,
    val selectedCategory: String,
    val textState: String,
    val exerciseStyleExpanded: Boolean,
    val selectedStyle: String,
) {
    companion object {
        fun getInitialState() = AddExerciseScreenState(
            exerciseTypeExpanded = false,
            selectedCategory = "어깨",
            textState = "",
            exerciseStyleExpanded = false,
            selectedStyle = ""
        )
    }
}

sealed class AddExerciseScreenSideEffect {
    object ShowError : AddExerciseScreenSideEffect()
    data class NavigateToSummary(val exerciseName: String, val category: String, val style: String) : AddExerciseScreenSideEffect()
}

sealed class AddExerciseIntent {
    data class SetExerciseName(val state: String) : AddExerciseIntent()
    data class SetExerciseCategory(val category: String) : AddExerciseIntent()
    data class SetExerciseStyle(val style: String) : AddExerciseIntent()
    object ToggleExerciseTypeDropdown : AddExerciseIntent()
    object ToggleExerciseStyleDropdown : AddExerciseIntent()
}
