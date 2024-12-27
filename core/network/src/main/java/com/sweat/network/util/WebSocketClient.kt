package com.sweat.network.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class WebSocketClient @Inject constructor(
    private val baseUrl: String,
    private val client: OkHttpClient,
    private val onMessageReceived: (String) -> Unit,
    private val onError: (Throwable) -> Unit,
    private val onClosed: () -> Unit
) {
    private var webSocket: WebSocket? = null
    private var retryCount = 0
    private val maxRetries = 3
    private val retryDelayMillis = 3000L

    private val scope = CoroutineScope(Dispatchers.IO)

    fun connect(roomName: String) {
        val url = "$baseUrl$roomName"
        val request = Request.Builder().url(url).build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                retryCount = 0
                println("WebSocket Opened")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                onMessageReceived(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                onError(t)
                if (retryCount < maxRetries) {
                    retryCount++
                    println("WebSocket failed, retrying ($retryCount/$maxRetries)...")
                    retryConnection(roomName)
                } else {
                    println("WebSocket failed, max retries reached.")
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                onClosed()
                if (retryCount < maxRetries) {
                    retryCount++
                    println("WebSocket closed, retrying ($retryCount/$maxRetries)...")
                    retryConnection(roomName)
                } else {
                    println("WebSocket closed, max retries reached.")
                }
            }
        })
    }

    private fun retryConnection(roomName: String) {
        scope.launch {
            delay(retryDelayMillis)
            connect(roomName)
        }
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closed by client")
    }
}
