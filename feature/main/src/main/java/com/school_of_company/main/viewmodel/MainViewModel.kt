package com.school_of_company.main.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.school_of_company.main.util.updateAtIndex
import com.sweat.common.base.BaseViewModel
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exerciseRoutineCheckUseCase: ExerciseRoutineCheckUseCase,
    private val foodRoutineCheckUseCase: FoodRoutineCheckUseCase,
    private val exerciseSetAddUseCase: ExerciseSetAddUseCase
) : BaseViewModel<MainState, MainSideEffect, MainIntent>(MainState.getDefaultState()) {
    override fun handleIntent(intent: MainIntent) {
        val currentDate = getCurrentDate()

        when (intent) {
            is MainIntent.UpdateSet -> setState {
                copy(
                    setList = setList.updateAtIndex(intent.id) { setStateList ->
                        setStateList.copy(
                            sets = setStateList.sets.toImmutableList().updateAtIndex(intent.set + 1) { setState ->
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
                exerciseSetAdd(body = body)
                exerciseRoutineCheck(date = currentDate)
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

    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }
}

data class MainState(
    val setList: ImmutableList<ExerciseRoutineResponseEntity>,
    val foodList: ImmutableList<FoodRoutineResponseEntity>,
    val addSet: ExerciseSetRequestParam
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
            )
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

    data class UpdateSet(
        val id: Int,
        val set: Int,
        val minute: Int?,
        val second: Int?,
        val weight: Int?,
        val count: Int?
    ) : MainIntent()
}