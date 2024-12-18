package com.school_of_company.main.view.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.math.atan2

@Composable
internal fun MainTimer(
    modifier: Modifier = Modifier,
    initialTime: Int,
    index: Int
) {
    SSBAndroidTheme { colors, typography ->
        val coroutineScope = rememberCoroutineScope()

        var timerState by remember { mutableStateOf(TimerState.STOPPED) }
        var timerValue by remember { mutableIntStateOf(initialTime) }

        val progress = remember { Animatable(1f) }
        var job by remember { mutableStateOf<Job?>(null) }

        LaunchedEffect(timerState) {
            when (timerState) {
                TimerState.STARTED, TimerState.REFRESH -> {

                    val remainingDuration = (timerValue * 1000 * progress.value).toLong()

                    job = coroutineScope.launch {
                        progress.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = remainingDuration.toInt())
                        )
                    }

                    while (timerValue > 0 && timerState == TimerState.STARTED) {
                        delay(1000L)
                        timerValue -= 1
                    }

                    if (timerValue == 0) {
                        timerState = TimerState.STOPPED
                    }
                }

                TimerState.STOPPED -> {
                    job?.cancel()
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (timerState == TimerState.REFRESH) "${index}세트 휴식!" else "${index}세트 시작!",
                style = typography.titleSmall,
                color = Color.Black
            )

            Spacer(modifier = Modifier.padding(bottom = 12.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(250.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            val dragAngle = atan2(
                                y = -dragAmount.y,
                                x = dragAmount.x
                            )

                            val dragDistance = dragAmount.getDistance()

                            val newTimerValue = timerValue + (dragDistance * if (dragAngle > 0) 1 else -1).toInt()

                            timerValue = newTimerValue.coerceIn(0, initialTime)

                            coroutineScope.launch {
                                progress.snapTo((timerValue.toFloat() / initialTime).coerceIn(0f, 1f))
                            }
                        }
                    }
            ) {
                CircularProgressIndicator(
                    progress = 1f,
                    strokeWidth = 4.dp,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxSize()
                )

                CircularProgressIndicator(
                    progress = 1f - progress.value,
                    strokeWidth = 4.dp,
                    color = when(timerState) {
                        TimerState.STARTED -> colors.main
                        TimerState.STOPPED -> colors.gray600
                        TimerState.REFRESH -> colors.blue
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = formatTimerText(timerValue),
                    style = typography.titleLarge,
                    color = when(timerState) {
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

                        coroutineScope.launch {
                            progress.snapTo(1f)
                        }
                    }
                )

                if (timerState == TimerState.STARTED) {
                    PlayIcon(
                        tint = colors.gray300,
                        modifier = Modifier.clickableSingle { timerState = TimerState.STOPPED }
                    )
                } else {
                    PauseIcon(
                        tint = colors.gray300,
                        modifier = Modifier.clickableSingle {
                            timerState = TimerState.STARTED
                            if (timerValue == 0) {
                                timerValue = initialTime
                                coroutineScope.launch { progress.snapTo(1f) }
                            }
                        }
                    )
                }

                SkipIcon(
                    tint = colors.gray300,
                    // todo : skip <- :main:tiemr 페이지 퍼블리싱할때 적용 / 지금 할 수 있는 부분이 아닌것 같다.. 하는 생각!
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
            index = 1
        )
    }
}