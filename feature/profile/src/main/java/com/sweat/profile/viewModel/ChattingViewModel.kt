package com.sweat.profile.viewModel

import android.util.Log
import com.sweat.common.base.BaseViewModel
import com.sweat.network.BuildConfig
import com.sweat.network.util.WebSocketClient
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import javax.inject.Inject


@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val okHttpClient: OkHttpClient,
) : BaseViewModel<ChattingState, ChattingScreenSideEffect, ChattingIntent>(ChattingState.getInitialState()) {

    private val webSocketClient = WebSocketClient(
        baseUrl = "${BuildConfig.BASE_URL}/ws/chat/".replace("https", "wss"),
        client = okHttpClient,
        onMessageReceived = { message ->
            Log.d("WebSocketClient", "Message received: $message")
        },
        onError = { error ->
            Log.e("WebSocketClient", "Error: ${error.message}")
        },
        onClosed = {
            Log.d("WebSocketClient", "Connection closed")
        }
    )

    // 웹소켓 클라이언트 초기화 및 연결
    private fun initializeWebSocket() {
        Log.d("WebSocket", "Initializing WebSocket...")
        webSocketClient.connect("1")  // 연결 시작
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
