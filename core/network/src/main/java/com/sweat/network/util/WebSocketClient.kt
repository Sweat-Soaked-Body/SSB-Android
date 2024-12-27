package com.sweat.network.util

import com.sweat.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.logging.HttpLoggingInterceptor

class WebSocketClient @Inject constructor(
    private val baseUrl: String,
    private val client: OkHttpClient,
    private val onMessageReceived: (String) -> Unit,
    private val onError: (Throwable) -> Unit,
    private val onClosed: () -> Unit
) {
    private var webSocket: WebSocket? = null
    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // FULL BODY를 로그로 확인
            }
        ) // 인터셉터 추가
        .build()
    private var retryCount = 0
    private val maxRetries = 3
    private val retryDelayMillis = 3000L

    private val scope = CoroutineScope(Dispatchers.IO)  // CoroutineScope 생성

    fun connect() {
        val url = "wss://${BuildConfig.BASE_URL}/chat/$roomName"
        val request = Request.Builder().url(url).build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                println("WebSocket Opened")
                retryCount = 0
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
                    retryConnection()
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
                } else {
                    println("WebSocket closed, max retries reached.")
                }
            }
        })
    }

    private fun retryConnection() {
        scope.launch {
            delay(retryDelayMillis)  // 재시도 간 대기 시간
            connect()  // 재연결 시도
        }
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Closed by client")
    }
}
