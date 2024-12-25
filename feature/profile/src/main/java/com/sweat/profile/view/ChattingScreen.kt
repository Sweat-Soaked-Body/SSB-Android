package com.sweat.profile.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.profile.viewModel.ChattingIntent
import com.sweat.profile.viewModel.ChattingState
import com.sweat.profile.viewModel.ChattingViewModel

@Composable
fun ChattingRoute(
    modifier: Modifier = Modifier,
    viewModel: ChattingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ChattingScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
    )
}
@Composable
fun ChattingScreen(
    modifier: Modifier = Modifier,
    state: ChattingState,
    handleIntent: (ChattingIntent) -> Unit,
) {
    var message by remember { mutableStateOf("") }  // 보내는 메시지
    var connectionStatus by remember { mutableStateOf("Disconnected") } // 웹소켓 연결 상태

    LaunchedEffect(Unit) {
        handleIntent(ChattingIntent.InitializeWebSocket)  // 웹소켓 URL 초기화
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 웹소켓 연결 상태 표시
        Text(text = "WebSocket Status: $connectionStatus", color = Color.Black)

        // 받은 메시지 표시
        Text(text = "Received Message: ${state.receivedMessage}", color = Color.Black)

        // 메시지 보내기 텍스트 필드
        BasicTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.padding(vertical = 16.dp),
            singleLine = true
        )

        // 메시지 보내기 버튼
        Button(onClick = {
            handleIntent(ChattingIntent.SendMessage(message))  // 메시지 전송
            message = ""  // 메시지 전송 후 입력창 비우기
        }) {
            Text("Send Message")
        }
    }
}
@Preview
@Composable
fun PreviewChattingScreen() {
    ChattingScreen(
        handleIntent = { _ -> },
        state = ChattingState.getInitialState(),
    )
}
