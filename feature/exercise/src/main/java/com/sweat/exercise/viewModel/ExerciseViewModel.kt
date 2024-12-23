package com.sweat.exercise.viewModel

import com.sweat.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(

) : BaseViewModel<ExerciseScreenState, ExerciseScreenSideEffect, ExerciseIntent>(ExerciseScreenState.getInitialState()) {

    init {
        val initialState = ExerciseScreenState.getInitialState()
        val filteredList = filterExercises(initialState.selectedButton, initialState.exerciseStateList)

        setState {
            initialState.copy(filteredExerciseStateList = filteredList)
        }
    }

    override fun handleIntent(intent: ExerciseIntent) {
        when (intent) {
            is ExerciseIntent.SetExerciseName -> setState { copy(searchTextState = intent.text) }
            is ExerciseIntent.SetExerciseCategory -> {
                setState { copy(selectedButton = intent.category) }
                val filteredList = filterExercises(intent.category, state.value.exerciseStateList)
                setState { copy(filteredExerciseStateList = filteredList) }
            }
            is ExerciseIntent.ToggleSearchMode -> setState { copy(isSearching = !isSearching) }
            is ExerciseIntent.UpdateExerciseItems -> updateExerciseItems(intent.items)
            ExerciseIntent.AddExercise -> postSideEffect(ExerciseScreenSideEffect.NavigateToAddExercise)
        }
    }

    private fun filterExercises(
        selectedCategory: String,
        exerciseStateList: ImmutableList<Triple<String, String, Boolean>>
    ): ImmutableList<Triple<String, String, Boolean>> {
        return if (selectedCategory == "전체") {
            exerciseStateList
        } else {
            exerciseStateList.filter { it.second == selectedCategory }.toImmutableList()
        }
    }

    private fun updateExerciseItems(newItems: ImmutableList<Triple<String, String, Boolean>>) {
        setState { copy(exerciseStateList = newItems) }
        val filteredList = filterExercises(state.value.selectedButton, newItems)
        setState { copy(filteredExerciseStateList = filteredList) }
    }
}


data class ExerciseScreenState(
    val exerciseList: ImmutableList<String>,
    val selectedButton: String,
    val exerciseStateList: ImmutableList<Triple<String, String, Boolean>> = persistentListOf(),
    val isSearching: Boolean,
    val searchTextState: String,
    val filteredExerciseStateList: ImmutableList<Triple<String, String, Boolean>> = persistentListOf()
) {
    companion object {
        fun getInitialState() = ExerciseScreenState(
            exerciseList = persistentListOf("전체", "어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타"),
            selectedButton = "전체",
            exerciseStateList = persistentListOf(
                Triple("운동 1", "어깨", false),
                Triple("운동 2", "등", true),
                Triple("운동 3", "가슴", false),
                Triple("운동 4", "하체", true)
            ),
            isSearching = false,
            searchTextState = "",
            filteredExerciseStateList = persistentListOf()
        )
    }
}

sealed class ExerciseScreenSideEffect {
    object NavigateToAddExercise : ExerciseScreenSideEffect()
}

sealed class ExerciseIntent {
    data class SetExerciseName(val text: String) : ExerciseIntent()
    data class SetExerciseCategory(val category: String) : ExerciseIntent()
    data class UpdateExerciseItems(val items: ImmutableList<Triple<String, String, Boolean>>) : ExerciseIntent()
    object ToggleSearchMode : ExerciseIntent()
    object AddExercise : ExerciseIntent()
}
