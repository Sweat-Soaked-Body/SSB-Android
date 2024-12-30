package com.sweat.exercise.viewModel

import com.sweat.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(

) : BaseViewModel<AddExerciseScreenState, AddExerciseScreenSideEffect, AddExerciseIntent>(AddExerciseScreenState.getInitialState()) {
    override fun handleIntent(intent: AddExerciseIntent) {
        when (intent) {
            is AddExerciseIntent.ExerciseName -> setState { copy(textState = intent.name) }
            is AddExerciseIntent.ExerciseCategory -> setState { copy(selectedCategory = intent.category) }
            is AddExerciseIntent.ExerciseStyle -> setState { copy(selectedStyle = intent.style) }
            is AddExerciseIntent.ToggleExerciseTypeDropdown -> setState { copy(exerciseTypeExpanded = !exerciseTypeExpanded) }
            is AddExerciseIntent.ToggleExerciseStyleDropdown -> setState { copy(exerciseStyleExpanded = !exerciseStyleExpanded) }
            AddExerciseIntent.navigateToExercise -> postSideEffect(AddExerciseScreenSideEffect.NavigateToExercise)
            AddExerciseIntent.PopUpBackStack -> postSideEffect(AddExerciseScreenSideEffect.PopUpBackStack)
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
    object NavigateToExercise : AddExerciseScreenSideEffect()
    object PopUpBackStack : AddExerciseScreenSideEffect()
}

sealed class AddExerciseIntent {
    data class ExerciseName(val name: String) : AddExerciseIntent()
    data class ExerciseCategory(val category: String) : AddExerciseIntent()
    data class ExerciseStyle(val style: String) : AddExerciseIntent()
    object ToggleExerciseTypeDropdown : AddExerciseIntent()
    object ToggleExerciseStyleDropdown : AddExerciseIntent()
    object navigateToExercise : AddExerciseIntent()
    object PopUpBackStack : AddExerciseIntent()
}
