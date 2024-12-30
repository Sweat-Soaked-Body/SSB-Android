package com.sweat.network.util

import kotlinx.coroutines.*
import okhttp3.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.sweat.network.dto.profile.ChatMessage
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class WebSocketClient(
    private val baseUrl: String,
    private val client: OkHttpClient,
    private val onSendSuccess: () -> Unit,
    private val onMessageReceived: (List<ChatMessage>) -> Unit,
    private val onError: (Throwable) -> Unit,
    private val onClosed: () -> Unit
) {
    private var webSocket: WebSocket? = null
    private var retryCount = 0
    private var isConnected = false
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // Moshi 인스턴스 및 어댑터 설정
    private val moshi =
        Moshi.Builder().add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()) // 추가
            .build()
    private val listType = Types.newParameterizedType(List::class.java, ChatMessage::class.java)
    private val adapter = moshi.adapter<List<ChatMessage>>(listType)

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
                try {
                    val messages = parseMessages(text)
                    onMessageReceived(messages)
                } catch (e: Exception) {
                    logError("Error parsing message: ${e.message}")
                    onError(e)
                }
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

    private fun parseMessages(json: String): List<ChatMessage> {
        val messages = adapter.fromJson(json) ?: emptyList()
        return messages.map { it.copy(timestamp = parseTimestamp(it.timestamp).toString()) }
    }

    private fun parseTimestamp(timestamp: String?): Long {
        return try {
            OffsetDateTime.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()
                .toEpochMilli()
        } catch (e: Exception) {
            logError("Failed to parse timestamp: $timestamp")
            0L
        }
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
        coroutineScope.cancel()
    }

    private fun logInfo(message: String) {
        println("$TAG: $message")
    }

    private fun logError(message: String) {
        System.err.println("$TAG: $message")
    }
}
