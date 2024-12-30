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
    okHttpClient: OkHttpClient,
) : BaseViewModel<ChattingState, ChattingScreenSideEffect, ChattingIntent>(ChattingState.getInitialState()) {

    private val webSocketClient = WebSocketClient(
        baseUrl = "${BuildConfig.BASE_URL}/ws/chat/".replace("https", "wss"),
        client = okHttpClient,
        onMessageReceived = { message ->
            Log.d("WebSocketClient", "Message received: $message")
            addMessageToList(message)
        },
        onError = { error ->
            Log.e("WebSocketClient", "Error: ${error.message}")
            setState { copy(errorMessage = error.message ?: "Unknown error") }
        },
        onClosed = {
            Log.d("WebSocketClient", "Connection closed")
        },
        onSendSuccess = {
            setState { copy(messageInputTextState = "") }
        },
    )

    override fun handleIntent(intent: ChattingIntent) {
        when (intent) {
            is ChattingIntent.SetMyName -> setState { copy(myName = intent.name) }
            is ChattingIntent.SendMessage -> sendMessage(intent.message)
            is ChattingIntent.InitializeWebSocket -> initializeWebSocket(intent.roomId)
            is ChattingIntent.SetMessageInputTextState -> setState { copy(messageInputTextState = intent.state) }
        }
    }

    private fun initializeWebSocket(roomName: String) {
        Log.d("WebSocket", "Initializing WebSocket...")
        webSocketClient.connect(roomName = roomName)
    }

    private fun sendMessage(message: String) {
        webSocketClient.sendMessage(message)
    }

    private fun addMessageToList(message: String) {
        val newMessage = ChatMessage(
            message = message,
            sender = "Server", // 기본값으로 설정 (필요에 따라 파싱)
            timestamp = System.currentTimeMillis() // 타임스탬프 추가
        )
        setState { copy(messages = messages + newMessage) }
    }
}

data class ChattingState(
    val myName: String,
    val messageInputTextState: String,
    val messages: List<ChatMessage>, // 메시지 리스트 추가
    val errorMessage: String?, // 에러 메시지 상태 추가
) {
    companion object {
        fun getInitialState() = ChattingState(
            myName = "",
            messageInputTextState = "",
            messages = emptyList(),
            errorMessage = null,
        )
    }
}

data class ChatMessage(
    val message: String,
    val sender: String,
    val timestamp: Long
)

sealed class ChattingScreenSideEffect

sealed class ChattingIntent {
    data class SetMyName(val name: String) : ChattingIntent()
    data class SendMessage(val message: String) : ChattingIntent()
    data class InitializeWebSocket(val roomId: String) : ChattingIntent()
    data class SetMessageInputTextState(val state: String) : ChattingIntent()
}
