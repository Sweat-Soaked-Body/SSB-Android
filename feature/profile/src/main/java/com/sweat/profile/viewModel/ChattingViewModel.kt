package com.sweat.profile.viewModel

import android.util.Log
import com.sweat.common.base.BaseViewModel
import com.sweat.network.util.WebSocketClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChattingViewModel @Inject constructor(
) : BaseViewModel<ChattingState, ChattingScreenSideEffect, ChattingIntent>(ChattingState.getInitialState()) {

    private var webSocketClient: WebSocketClient = WebSocketClient(
        roomName = "1",
        onMessageReceived = { message ->
            setState { copy(receivedMessage = message) }   // 메시지 수신 시 상태 업데이트
            Log.d("WebSocket", "Received message: $message")
        },
        onError = { error ->
            Log.e("WebSocket", "Error occurred: ${error.message}")
            // 연결 상태를 'Error'로 업데이트
            setState { copy(receivedMessage = "Error: ${error.message}") }
        },
        onClosed = {
            Log.d("WebSocket", "Connection closed")
            // 연결 상태를 'Closed'로 업데이트
            setState { copy(receivedMessage = "Connection closed") }
        }
    )

    // 웹소켓 클라이언트 초기화 및 연결
    private fun initializeWebSocket() {
        Log.d("WebSocket", "Initializing WebSocket...")
        webSocketClient.connect()  // 연결 시작
    }

    // 메시지 전송 함수
    private fun sendMessage(message: String) {
        Log.d("WebSocket", "Sending message: $message")
        webSocketClient.sendMessage(message)
    }

    override fun handleIntent(intent: ChattingIntent) {
        when (intent) {
            is ChattingIntent.SetMyName -> setState { copy(myName = intent.name) }
            is ChattingIntent.SendMessage -> sendMessage(intent.message)
            is ChattingIntent.InitializeWebSocket -> initializeWebSocket()
        }
    }
}

data class ChattingState(
    val myName: String,
    val receivedMessage: String,
) {
    companion object {
        fun getInitialState() = ChattingState(
            myName = "",
            receivedMessage = ""
        )
    }
}

sealed class ChattingScreenSideEffect

sealed class ChattingIntent {
    data class SetMyName(val name: String) : ChattingIntent()
    data class SendMessage(val message: String) : ChattingIntent()
    object InitializeWebSocket : ChattingIntent()
}
