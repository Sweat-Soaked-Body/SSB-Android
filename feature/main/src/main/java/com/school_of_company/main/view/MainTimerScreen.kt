package com.school_of_company.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school_of_company.main.view.component.MainTimer
import com.school_of_company.main.view.component.MainTimerExerciseList
import com.school_of_company.main.view.component.exercise
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.component.topbar.SSBTopBar
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MainTimerRoute(
    popUpBackStack: () -> Unit,
) {
    MainTimerScreen(
        popUpBackStack = popUpBackStack,
    )
}

@Composable
private fun MainTimerScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
) {
    var currentSet by remember { mutableIntStateOf(1) } // 현재 세트 상태
    val exerciseList = persistentListOf(
        exercise(weight = 55, time = 4),
        exercise(weight = 60, time = 5),
        exercise(weight = 65, time = 6),
        exercise(weight = 70, time = 7),
        exercise(weight = 75, time = 8),
    )

    SSBAndroidTheme { colors, typography ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.white)
                .padding(top = 15.dp)
        ) {
            SSBTopBar(
                startIcon = { ChevronLeftIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
                betweenText = "바벨 벡스쿼트",
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            Spacer(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = colors.gray100
                    )
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Spacer(modifier = Modifier.padding(top = 30.dp))

            // Main Timer
            MainTimer(
                initialTime = 60,
                index = currentSet,
                nextOnClick = {
                    if (currentSet < exerciseList.size) {
                        currentSet += 1 // 다음 세트로 이동
                    }
                }
            )

            Spacer(modifier = Modifier.height(52.dp))

            Spacer(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = colors.gray100
                    )
                    .fillMaxWidth()
                    .height(1.dp)
            )

            // Exercise List
            MainTimerExerciseList(
                data = exerciseList,
                currentSet = currentSet
            )
        }
    }
}

@Preview
@Composable
private fun MainTimerScreenPreview() {
    MainTimerScreen(
        popUpBackStack = {},
    )
}