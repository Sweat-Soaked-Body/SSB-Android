package com.sweat.exercise.viewModel

import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.exercise.ExerciseAddUseCase
import com.sweat.model.param.exercise.ExerciseAddRequestParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val exerciseAddUseCase: ExerciseAddUseCase
) : BaseViewModel<AddExerciseScreenState, AddExerciseScreenSideEffect, AddExerciseIntent>(AddExerciseScreenState.getInitialState()) {
    override fun handleIntent(intent: AddExerciseIntent) {
        when (intent) {
            is AddExerciseIntent.ExerciseName -> setState { copy(textState = intent.name) }
            is AddExerciseIntent.ExerciseCategory -> setState { copy(selectedCategory = intent.category) }
            is AddExerciseIntent.ToggleExerciseTypeDropdown -> setState { copy(exerciseTypeExpanded = !exerciseTypeExpanded) }
            AddExerciseIntent.navigateToExercise -> postSideEffect(AddExerciseScreenSideEffect.NavigateToExercise)
            AddExerciseIntent.PopUpBackStack -> postSideEffect(AddExerciseScreenSideEffect.PopUpBackStack)
            is AddExerciseIntent.AddExercise -> addExercise(intent.exerciseAddRequest)
        }
    }

    private fun addExercise(exerciseAddRequest: ExerciseAddRequestParam) {
        viewModelScope.launch {
            exerciseAddUseCase(exerciseAddRequest).onSuccess {
                it.catch {
                    postSideEffect(AddExerciseScreenSideEffect.ExerciseAddFailed)
                }.collect {
                    postSideEffect(AddExerciseScreenSideEffect.ExerciseAddSuccess)
                }
            }.onFailure {
                postSideEffect(AddExerciseScreenSideEffect.ExerciseAddFailed)
            }
        }
    }
}

data class AddExerciseScreenState(
    val exerciseTypeExpanded: Boolean,
    val selectedCategory: Int,
    val textState: String,
    val categories: ImmutableList<String>
) {
    companion object {
        fun getInitialState() = AddExerciseScreenState(
            exerciseTypeExpanded = false,
            selectedCategory = 0,
            textState = "",
            categories = persistentListOf("어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타")
        )
    }
}

sealed class AddExerciseScreenSideEffect {
    object NavigateToExercise : AddExerciseScreenSideEffect()
    object PopUpBackStack : AddExerciseScreenSideEffect()
    object ExerciseAddSuccess : AddExerciseScreenSideEffect()
    object ExerciseAddFailed : AddExerciseScreenSideEffect()
}

sealed class AddExerciseIntent {
    data class ExerciseName(val name: String) : AddExerciseIntent()
    data class ExerciseCategory(val category: Int) : AddExerciseIntent()
    data class AddExercise(val exerciseAddRequest: ExerciseAddRequestParam) : AddExerciseIntent()
    object ToggleExerciseTypeDropdown : AddExerciseIntent()
    object navigateToExercise : AddExerciseIntent()
    object PopUpBackStack : AddExerciseIntent()
}
