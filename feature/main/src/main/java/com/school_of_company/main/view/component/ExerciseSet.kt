package com.school_of_company.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school_of_company.main.enum.ExerciseActionType
import com.school_of_company.main.enum.ExerciseSetState
import com.school_of_company.main.viewmodel.SetState
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.HamburgerIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ExerciseSet(
    modifier: Modifier = Modifier,
    state: ImmutableList<SetState>,
    onSetChange: (Int, Int?, Int?, Int?, Int?) -> Unit
) {
    var exerciseActionType  by remember { mutableStateOf(ExerciseActionType.ADDSET) }
    var exerciseSetState  by remember { mutableStateOf(ExerciseSetState.VIEW) }

    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colors.white, RoundedCornerShape(size = 12.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "바벨 백스쿼트",
                    style = typography.bodyMedium,
                    color = colors.black
                )

                HamburgerIcon(modifier = Modifier.clickableSingle { /*TODO*/ })
            }

            Spacer(modifier = Modifier.height(13.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when(exerciseSetState) {
                    ExerciseSetState.VIEW -> {
                        state.forEach { state ->
                            ExerciseSetItem(
                                set = state.set,
                                minute = state.minute,
                                second = state.second,
                                weight = state.weight,
                                count = state.count
                            )
                        }

                        exerciseActionType = ExerciseActionType.REMOVEADDSET
                    }

                    ExerciseSetState.UPDATE -> {
                        state.forEach { state ->
                            ExerciseSetChangeItem(
                                set = state.set,
                                minute = state.minute,
                                second = state.second,
                                weight = state.weight,
                                count = state.count,
                                onStateChange = { minute, second, weight, count ->
                                    onSetChange(state.set, minute, second, weight, count)
                                }
                            )
                        }

                        exerciseActionType = ExerciseActionType.UPDATESET
                    }

                    ExerciseSetState.ADD -> {
                        state.forEach { state ->
                            ExerciseSetItem(
                                set = state.set,
                                minute = state.minute,
                                second = state.second,
                                weight = state.weight,
                                count = state.count
                            )
                        }

                        val lastSetNumber = state.lastOrNull()?.set ?: 0
                        val lastWeight = state.lastOrNull()?.weight

                        if(lastWeight == null) {
                            ExerciseSetChangeItem(
                                set = lastSetNumber + 1,
                                weight = null,
                                count = null,
                                minute = 0,
                                second = 0,
                                onStateChange = { minute, second, weight, count ->
                                    onSetChange(lastSetNumber + 1, minute, second, weight, count)
                                }
                            )
                        } else {
                            ExerciseSetChangeItem(
                                set = lastSetNumber + 1,
                                weight = 0,
                                count = 0,
                                minute = null,
                                second = null,
                                onStateChange = { minute, second, weight, count ->
                                    onSetChange(lastSetNumber + 1, minute, second, weight, count)
                                }
                            )
                        }

                        exerciseActionType = ExerciseActionType.ADDSET
                    }
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                when(exerciseActionType) {
                    ExerciseActionType.ADDSET -> {
                        Text(
                            modifier = Modifier.clickableSingle { /*TODO*/ },
                            text = "추가 완료",
                            style = typography.label,
                            color = colors.gray300
                        )
                    }
                    ExerciseActionType.REMOVEADDSET -> {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(52.dp)
                        ) {
                            Text(
                                modifier = Modifier.clickableSingle { /*TODO*/ },
                                text = "- 세트 삭제",
                                style = typography.label,
                                color = colors.gray300
                            )
                            Text(
                                modifier = Modifier.clickableSingle { exerciseSetState = ExerciseSetState.ADD },
                                text = "+ 세트 추가",
                                style = typography.label,
                                color = colors.gray300
                            )
                        }
                    }
                    ExerciseActionType.UPDATESET -> {
                        Text(
                            modifier = Modifier.clickableSingle { /*TODO*/ },
                            text = "수정 완료",
                            style = typography.label,
                            color = colors.gray300
                        )
                    }
                }
            }
        }
    }
}