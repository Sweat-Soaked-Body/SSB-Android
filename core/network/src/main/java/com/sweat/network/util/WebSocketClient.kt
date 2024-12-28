package com.sweat.network.util

import kotlinx.coroutines.*
import okhttp3.*

class WebSocketClient(
    private val baseUrl: String,
    private val client: OkHttpClient,
    private val onSendSuccess: () -> Unit,
    private val onMessageReceived: (String) -> Unit,
    private val onError: (Throwable) -> Unit,
    private val onClosed: () -> Unit
) {
    private var webSocket: WebSocket? = null
    private var retryCount = 0
    private var isConnected = false // 연결 상태 추적
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // 상수 선언
    companion object {
        private const val MAX_RETRIES = 3
        private const val RETRY_DELAY_MILLIS = 3000L
        private const val NORMAL_CLOSURE_STATUS = 1000
        private const val TAG = "WebSocketClient"
    }

    fun connect(roomName: String) {
        if (isConnected) {
            logInfo("WebSocket already connected.")
            return
        }

        val url = "$baseUrl$roomName"
        val request = Request.Builder().url(url).build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                retryCount = 0
                isConnected = true
                logInfo("WebSocket opened successfully.")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                logInfo("Message received: $text")
                onMessageReceived(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                isConnected = false
                logError("WebSocket failure: ${t.message}")
                onError(t)
                retryConnectionIfNeeded(roomName)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                isConnected = false
                logInfo("WebSocket closed: $code - $reason")
                onClosed()
                retryConnectionIfNeeded(roomName)
            }
        })
    }

    private fun retryConnectionIfNeeded(roomName: String) {
        if (retryCount < MAX_RETRIES) {
            retryCount++
            logInfo("Retrying connection ($retryCount/$MAX_RETRIES) after $RETRY_DELAY_MILLIS ms...")
            coroutineScope.launch {
                delay(RETRY_DELAY_MILLIS)
                connect(roomName)
            }
        } else {
            logError("Max retries reached. WebSocket connection failed.")
        }
    }

    fun sendMessage(message: String) {
        if (isConnected) {
            webSocket?.send(message)
            logInfo("Message sent: $message")
            onSendSuccess()
        } else {
            logError("Cannot send message. WebSocket is not connected.")
        }
    }

    fun close() {
        logInfo("Closing WebSocket...")
        isConnected = false
        webSocket?.close(NORMAL_CLOSURE_STATUS, "Closed by client")
        coroutineScope.cancel() // 코루틴 스코프 종료
    }

    private fun logInfo(message: String) {
        println("$TAG: $message") // Logger 사용 가능 (Log.d 등)
    }

    private fun logError(message: String) {
        System.err.println("$TAG: $message") // Logger 사용 가능 (Log.e 등)
    }
}
