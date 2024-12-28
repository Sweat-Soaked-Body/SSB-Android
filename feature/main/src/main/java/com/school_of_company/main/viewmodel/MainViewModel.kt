package com.school_of_company.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.school_of_company.main.enum.SetStatus
import com.school_of_company.main.util.updateAtIndex
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.main.ExerciseRoutineCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exerciseRoutineCheckUseCase: ExerciseRoutineCheckUseCase
) : BaseViewModel<MainState, MainSideEffect, MainIntent>(MainState.getDefaultState()) {
    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.UpdateSet ->setState {
                copy(
                    setList = setList.updateAtIndex(intent.id) { setStateList ->
                        setStateList.copy(
                            state = setStateList.state.updateAtIndex(intent.set + 1) { setState ->
                                setState.copy(
                                    minute = intent.minute,
                                    second = intent.second,
                                    weight = intent.weight,
                                    count = intent.count
                                )
                            }
                        )
                    }
                )
            }
            is MainIntent.ExerciseRoutineCheck -> {
                val currentDate = getCurrentDate()
                exerciseRoutineCheck(date = currentDate)
            }
        }
    }

    private fun exerciseRoutineCheck(date:String) {
        viewModelScope.launch {
            exerciseRoutineCheckUseCase(date = date).onSuccess {
                it.catch {
                    postSideEffect(MainSideEffect.ExerciseRoutineCheckFailed)
                }.collect {
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
    val setList: ImmutableList<SetStateList>
) {
    companion object {
        fun getDefaultState() = MainState(
            setList = persistentListOf()
        )
    }
}

data class CalendarState(
    val weekOfYear: Int,
    val month: String,
    val weekDays: List<String>,
    val currentWeekDay: Int
)

data class SetStateList(
    val id: Int,
    val state: ImmutableList<SetState>
)

data class SetState(
    val set: Int,
    val routine: Int,
    val minute: Int?,
    val second: Int?,
    val weight: Int?,
    val count: Int?,
    val status: SetStatus
)

sealed class MainSideEffect {
    object ExerciseRoutineCheckSuccess : MainSideEffect()
    object ExerciseRoutineCheckFailed : MainSideEffect()
}

sealed class MainIntent {
    data class ExerciseRoutineCheck(val date: String): MainIntent()

    data class UpdateSet(
        val id: Int,
        val set: Int,
        val minute: Int?,
        val second: Int?,
        val weight: Int?,
        val count: Int?
    ) : MainIntent()
}