package com.sweat.exercise.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.exercise.ExerciseLikeRequestUseCase
import com.sweat.domain.exercise.ExerciseListUseCase
import com.sweat.model.param.exercise.ExerciseLikeRequestParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseListUseCase: ExerciseListUseCase,
    private val exerciseLikeRequestUseCase: ExerciseLikeRequestUseCase
) : BaseViewModel<ExerciseScreenState, ExerciseScreenSideEffect, ExerciseIntent>(ExerciseScreenState.getInitialState()) {

    init {
        loadExercises(1)
    }

    private fun loadExercises(id: Int) {
        // 운동 목록 로딩
        viewModelScope.launch {
            exerciseListUseCase(id).collect { exercises ->
                setState {
                    copy(
                        exerciseStateList = exercises.map {
                            ExerciseItem(it.id, it.category, it.name, it.like)
                        }.toImmutableList()
                    )
                }
                setState {
                    copy(
                        filteredExerciseStateList = exercises.map {
                            ExerciseItem(it.id, it.category, it.name, it.like)
                        }.toImmutableList()
                    )
                }
            }
        }
    }

    override fun handleIntent(intent: ExerciseIntent) {
        when (intent) {
            is ExerciseIntent.SetExerciseName -> {
                setState { copy(searchTextState = intent.text) }
                val filteredList = filterExercises(state.value.selectedButton, intent.text, state.value.exerciseStateList)
                setState { copy(filteredExerciseStateList = filteredList) }
            }
            is ExerciseIntent.SetExerciseCategory -> {
                setState { copy(selectedButton = intent.category) }
                val filteredList = filterExercises(intent.category, state.value.searchTextState, state.value.exerciseStateList)
                setState { copy(filteredExerciseStateList = filteredList) }
            }
            is ExerciseIntent.ToggleSearchMode -> setState { copy(isSearching = !isSearching) }
            is ExerciseIntent.UpdateExerciseItems -> updateExerciseItems(intent.items)
            is ExerciseIntent.ToggleLikeStatus -> {
                toggleLikeStatus(ExerciseLikeRequestParam(intent.exerciseId))
            }
            ExerciseIntent.AddExercise -> postSideEffect(ExerciseScreenSideEffect.NavigateToAddExercise)
        }
    }


    private fun toggleLikeStatus(body: ExerciseLikeRequestParam) {
        val updatedList = state.value.exerciseStateList.toMutableList()
        val currentItemIndex = updatedList.indexOfFirst { it.id == body.exercise }

        if (currentItemIndex != -1) {
            val currentItem = updatedList[currentItemIndex]
            val toggledItem = currentItem.copy(like = !currentItem.like)

            updatedList[currentItemIndex] = toggledItem
            setState { copy(exerciseStateList = updatedList.toImmutableList()) }

            viewModelScope.launch {
                exerciseLikeRequestUseCase(body = body).onSuccess {
                    it.catch {
                        postSideEffect(ExerciseScreenSideEffect.ExerciseLikeFailed)
                    }.collect {
                        postSideEffect(ExerciseScreenSideEffect.ExerciseLikeSuccess)
                    }
                }.onFailure {
                    postSideEffect(ExerciseScreenSideEffect.ExerciseLikeFailed)
                }
            }
        }
    }

    private fun filterExercises(
        selectedCategory: Int,
        searchText: String,
        exerciseStateList: ImmutableList<ExerciseItem>
    ): ImmutableList<ExerciseItem> {
        return exerciseStateList.filter { exercise ->
            val matchesCategory = selectedCategory == -1 || exercise.category == selectedCategory
            val matchesSearchText = if (searchText.isEmpty()) true else exercise.name.contains(searchText, ignoreCase = true)
            matchesCategory && matchesSearchText
        }.toImmutableList()
    }

    private fun updateExerciseItems(newItems: ImmutableList<ExerciseItem>) {
        setState { copy(exerciseStateList = newItems) }
        val filteredList = filterExercises(state.value.selectedButton, state.value.searchTextState, newItems)
        setState { copy(filteredExerciseStateList = filteredList) }
    }
}

data class ExerciseScreenState(
    val exerciseList: ImmutableList<String>,
    val selectedButton: Int,
    val exerciseStateList: ImmutableList<ExerciseItem> = persistentListOf(),
    val isSearching: Boolean,
    val searchTextState: String,
    val filteredExerciseStateList: ImmutableList<ExerciseItem> = persistentListOf()
) {
    companion object {
        fun getInitialState() = ExerciseScreenState(
            exerciseList = persistentListOf("전체", "어깨", "등", "가슴", "하체", "팔", "역도", "복근", "유산소", "기타"),
            selectedButton = -1,
            exerciseStateList = persistentListOf(),
            isSearching = false,
            searchTextState = "",
            filteredExerciseStateList = persistentListOf()
        )
    }
}

sealed class ExerciseScreenSideEffect {
    object NavigateToAddExercise : ExerciseScreenSideEffect()
    object ExerciseLikeSuccess : ExerciseScreenSideEffect()
    object ExerciseLikeFailed : ExerciseScreenSideEffect()
}

data class ExerciseItem(
    val id: Int,
    val category: Int,
    val name: String,
    val like: Boolean
)

sealed class ExerciseIntent {
    data class SetExerciseName(val text: String) : ExerciseIntent()
    data class SetExerciseCategory(val category: Int) : ExerciseIntent()
    data class UpdateExerciseItems(val items: ImmutableList<ExerciseItem>) : ExerciseIntent()
    data class ToggleLikeStatus(val exerciseId: Int) : ExerciseIntent()
    object ToggleSearchMode : ExerciseIntent()
    object AddExercise : ExerciseIntent()
}
