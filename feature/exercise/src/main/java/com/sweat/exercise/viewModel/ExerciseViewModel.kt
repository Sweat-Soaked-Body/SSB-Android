package com.sweat.exercise.viewModel

import com.sweat.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(

) : BaseViewModel<ExerciseScreenState, ExerciseScreenSideEffect, ExerciseIntent>(ExerciseScreenState.getInitialState()) {
    override fun handleIntent(intent: ExerciseIntent) {
        when (intent) {
            is ExerciseIntent.SetExerciseName -> setState { copy(searchTextState = intent.text) }
            is ExerciseIntent.SetExerciseCategory -> setState { copy(selectedButton = intent.category) }
            is ExerciseIntent.ToggleSearchMode -> setState { copy(isSearching = !isSearching) }
            is ExerciseIntent.UpdateExerciseItems -> updateExerciseItems(intent.items)
        }
    }

    fun updateExerciseItems(newItems: ImmutableList<Pair<String, Boolean>>) {
        setState { copy(exerciseStateList = newItems) }
    }

    fun selectCategory(category: String) {
        setState { copy(selectedButton = category) }
    }

    fun setSearchText(text: String) {
        setState { copy(searchTextState = text) }
    }
}

data class ExerciseScreenState(
    val exerciseList: ImmutableList<String>,
    val selectedButton: String,
    val exerciseStateList: ImmutableList<Pair<String, Boolean>> = persistentListOf(),
    val isSearching: Boolean,
    val searchTextState: String
) {
    companion object {
        fun getInitialState() = ExerciseScreenState(
            exerciseList = persistentListOf("전체", "어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타"),
            selectedButton = "전체",
            exerciseStateList = persistentListOf(),
            isSearching = false,
            searchTextState = ""
        )
    }
}


sealed class ExerciseScreenSideEffect {
    object ShowError : ExerciseScreenSideEffect()
}

sealed class ExerciseIntent {
    data class SetExerciseName(val text: String) : ExerciseIntent()
    data class SetExerciseCategory(val category: String) : ExerciseIntent()
    data class UpdateExerciseItems(val items: ImmutableList<Pair<String, Boolean>>) : ExerciseIntent()
    object ToggleSearchMode : ExerciseIntent()
}