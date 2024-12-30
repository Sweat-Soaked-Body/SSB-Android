package com.school_of_company.main.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.school_of_company.main.util.updateAtIndex
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.main.DeleteExerciseRoutineUseCase
import com.sweat.domain.main.DeleteExerciseSetUseCase
import com.sweat.domain.main.ExerciseRoutineCheckUseCase
import com.sweat.domain.main.ExerciseSetAddUseCase
import com.sweat.domain.main.FoodRoutineCheckUseCase
import com.sweat.model.entity.main.ExerciseRoutineResponseEntity
import com.sweat.model.entity.main.FoodRoutineResponseEntity
import com.sweat.model.param.main.ExerciseSetRequestParam
import com.sweat.network.dto.main.ExerciseSetRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.nio.file.Files.copy
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exerciseRoutineCheckUseCase: ExerciseRoutineCheckUseCase,
    private val foodRoutineCheckUseCase: FoodRoutineCheckUseCase,
    private val exerciseSetAddUseCase: ExerciseSetAddUseCase,
    private val deleteExerciseSetUseCase: DeleteExerciseSetUseCase,
    private val deleteExerciseRoutineUseCase: DeleteExerciseRoutineUseCase
) : BaseViewModel<MainState, MainSideEffect, MainIntent>(MainState.getDefaultState()) {

    val swipeRefreshLoading = MutableStateFlow(false)
    private val currentDate = getCurrentDate()

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.UpdateSet -> setState {
                copy(
                    setList = setList.updateAtIndex(intent.id) { setStateList ->
                        setStateList.copy(
                            sets = setStateList.sets.toImmutableList().updateAtIndex(intent.set) { setState ->
                                setState.copy(
                                    min = intent.minute,
                                    sec = intent.second,
                                    weight = intent.weight,
                                    count = intent.count
                                )
                            }
                        )
                    }
                )
            }
            is MainIntent.ExerciseRoutineCheck -> {
                exerciseRoutineCheck(date = currentDate)
            }
            is MainIntent.FoodRoutineCheck -> {
                foodRoutineCheck(date = currentDate)
            }
            is MainIntent.ExerciseRoutineDelete -> {

            }
            is MainIntent.ExerciseSetDelete -> {

            }
            is MainIntent.ExerciseSetAdd-> {
                val body = ExerciseSetRequestParam(
                    routine = intent.routine,
                    weight = intent.weight,
                    count = intent.count,
                    min = intent.minute,
                    sec = intent.second
                )
                viewModelScope.launch {
                    exerciseSetAdd(body = body)
                    exerciseRoutineCheck(date = currentDate)
                }
            }
            is MainIntent.DeleteExerciseSet -> {
                viewModelScope.launch {
                    deleteExerciseSet(setId = intent.setId)
                    exerciseRoutineCheck(date = currentDate)
                }
            }
            is MainIntent.DeleteExerciseRoutine -> {
                viewModelScope.launch {
                    deleteExerciseRoutine(routineId = intent.routineId)
                    exerciseRoutineCheck(date = currentDate)
                    setState { copy(isShowExerciseBottomSheet = false) }
                }
            }
            is MainIntent.Setting -> {
                setState { copy(isShowExerciseBottomSheet = true) }
                setState { copy(currentRoutineId = intent.routineId) }
            }
            is MainIntent.HideBottomSheet -> {
                setState { copy(isShowExerciseBottomSheet = false) }
            }
        }
    }

    private fun exerciseRoutineCheck(date: String) {
        viewModelScope.launch {
            exerciseRoutineCheckUseCase(date = date).onSuccess {
                it.catch {
                    Log.e("MainViewModel", "Error during request: ${it.message}")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
                }.collect {
                    Log.d("MainViewModel", "Exercise routine check success")
                    setState {
                        copy(
                            setList = it.toImmutableList()
                        )
                    }
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckSuccess)
                }
            }.onFailure {
                postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
            }
        }
    }

    private fun foodRoutineCheck(date: String) {
        viewModelScope.launch {
            foodRoutineCheckUseCase(date = date).onSuccess {
                it.catch {
                    Log.e("MainViewModel", "Error during request: ${it.message}")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
                }.collect {
                    Log.d("MainViewModel", "food routine check success")
                    setState {
                        copy(
                            foodList = it.toImmutableList()
                        )
                    }
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckSuccess)
                }
            }.onFailure {
                postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
            }
        }
    }

    private fun exerciseSetAdd(body: ExerciseSetRequestParam) {
        viewModelScope.launch {
            exerciseSetAddUseCase(body = body).onSuccess {
                it.catch {
                    Log.e("MainViewModel", "Error during request: ${it.message}")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
                }.collect {
                    Log.d("MainViewModel", "food routine check success")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckSuccess)
                }
            }.onFailure {
                postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
            }
        }
    }

    private fun deleteExerciseSet(setId: Int) {
        viewModelScope.launch {
            deleteExerciseSetUseCase(setId = setId).onSuccess {
                it.catch {
                    Log.e("MainViewModel", "Error during request: ${it.message}")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
                }.collect {
                    Log.d("MainViewModel", "delete check success")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckSuccess)
                }
            }.onFailure {
                postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
            }
        }
    }

    private fun deleteExerciseRoutine(routineId: Int) {
        viewModelScope.launch {
            deleteExerciseRoutineUseCase(routineId = routineId).onSuccess {
                it.catch {
                    Log.e("MainViewModel", "Error during request: ${it.message}")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
                }.collect {
                    Log.d("MainViewModel", "delete routine check success")
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckSuccess)
                }
            }.onFailure {
                postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
            }
        }
    }

    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now(java.time.ZoneId.of("Asia/Seoul"))
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }

    fun loadStuff() {
        viewModelScope.launch {
            swipeRefreshLoading.value = true
            exerciseRoutineCheck(date = currentDate)
            foodRoutineCheck(date = currentDate)
            swipeRefreshLoading.value = false
        }
    }
}

data class MainState(
    val setList: ImmutableList<ExerciseRoutineResponseEntity>,
    val foodList: ImmutableList<FoodRoutineResponseEntity>,
    val addSet: ExerciseSetRequestParam,
    val isShowExerciseBottomSheet: Boolean,
    val currentRoutineId: Int
) {
    companion object {
        fun getDefaultState() = MainState(
            setList = persistentListOf(),
            foodList = persistentListOf(),
            addSet = ExerciseSetRequestParam(
                routine = 0,
                weight = null,
                count = null,
                min = null,
                sec = null
            ),
            isShowExerciseBottomSheet = false,
            currentRoutineId = 0
        )
    }
}

data class CalendarState(
    val weekOfYear: Int,
    val month: String,
    val weekDays: List<String>,
    val currentWeekDay: Int
)

sealed class MainSideEffect {
    object ExerciseRoutineCheckSuccess : MainSideEffect()

    object ExerciseRoutineCheckFailed : MainSideEffect()
}

sealed class MainIntent {
    object HideBottomSheet : MainIntent()

    data class Setting(val routineId: Int) : MainIntent()

    data class ExerciseRoutineCheck(val date: String) : MainIntent()

    data class ExerciseRoutineDelete(val routineId: Int): MainIntent()

    data class ExerciseSetDelete(val setId: Int): MainIntent()

    data class ExerciseSetAdd(
        val routine: Int,
        val weight: Int?,
        val count: Int?,
        val minute: Int?,
        val second: Int?
    ): MainIntent()

    data class FoodRoutineCheck(val date: String): MainIntent()

    data class DeleteExerciseSet(val setId: Int): MainIntent()

    data class DeleteExerciseRoutine(val routineId: Int): MainIntent()

    data class UpdateSet(
        val id: Int,
        val set: Int,
        val minute: Int?,
        val second: Int?,
        val weight: Int?,
        val count: Int?
    ) : MainIntent()
}