package com.school_of_company.main.view.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school_of_company.main.enum.TimerState
import com.school_of_company.main.util.formatTimerText
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.PauseIcon
import com.sweat.design_system.icon.PlayIcon
import com.sweat.design_system.icon.SkipIcon
import com.sweat.design_system.icon.StopIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.design_system.theme.SSBTypography
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.math.atan2

@Composable
internal fun MainTimer(
    modifier: Modifier = Modifier,
    initialTime: Int,
    index: Int,
    nextOnClick: () -> Unit
) {
    SSBAndroidTheme { colors, typography ->
        val coroutineScope = rememberCoroutineScope()

        var timerState by remember { mutableStateOf(TimerState.STOPPED) }
        var timerValue by remember { mutableIntStateOf(initialTime) }

        val progress = remember { Animatable(1f) }
        var job by remember { mutableStateOf<Job?>(null) }

        LaunchedEffect(timerState) {
            when (timerState) {
                TimerState.STARTED -> {
                    job = coroutineScope.launch {
                        while (timerValue > 0 && timerState == TimerState.STARTED) {
                            delay(1000L)
                            timerValue -= 1
                            progress.snapTo(timerValue.toFloat() / initialTime)
                        }

                        if (timerValue == 0) {
                            timerState = TimerState.REFRESH
                            nextOnClick() // 다음 세트로 이동
                        }
                    }
                }

                TimerState.STOPPED -> {
                    job?.cancel()
                }

                TimerState.REFRESH -> {
                    timerValue = initialTime // 타이머 초기화
                    progress.snapTo(1f)
                }
            }
        }

        // UI
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (timerState == TimerState.REFRESH) "${index}세트 완료!" else "${index}세트 시작!",
                style = typography.titleSmall,
                color = Color.Black
            )

            Spacer(modifier = Modifier.padding(bottom = 12.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(250.dp)
            ) {
                CircularProgressIndicator(
                    progress = progress.value,
                    strokeWidth = 4.dp,
                    color = when (timerState) {
                        TimerState.STARTED -> colors.main
                        TimerState.STOPPED -> colors.gray600
                        TimerState.REFRESH -> colors.blue
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = formatTimerText(timerValue),
                    style = typography.titleLarge,
                    color = when (timerState) {
                        TimerState.STARTED -> colors.main
                        TimerState.STOPPED -> colors.gray600
                        TimerState.REFRESH -> colors.blue
                    }
                )
            }

            Spacer(modifier = Modifier.padding(top = 30.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StopIcon(
                    tint = colors.gray300,
                    modifier = Modifier.clickableSingle {
                        timerValue = initialTime
                        timerState = TimerState.STOPPED
                        coroutineScope.launch { progress.snapTo(1f) }
                    }
                )

                if (timerState == TimerState.STARTED) {
                    PauseIcon(
                        tint = colors.gray300,
                        modifier = Modifier.clickableSingle {
                            timerState = TimerState.STOPPED
                        }
                    )
                } else {
                    PlayIcon(
                        tint = colors.gray300,
                        modifier = Modifier.clickableSingle {
                            timerState = TimerState.STARTED
                        }
                    )
                }

                SkipIcon(
                    tint = colors.gray300,
                    modifier = Modifier.clickableSingle {
                        timerState = TimerState.REFRESH
                        nextOnClick()
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun MainTimerPreview() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MainTimer(
            modifier = Modifier,
            initialTime = 100,
            index = 1,
            nextOnClick = {}
        )
    }
}