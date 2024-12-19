package com.sweat.network.util

import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val moshi: Moshi,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val ignorePath = listOf("/auth")
        val path = request.url.encodedPath

        if (ignorePath.contains(path)) {
            return chain.proceed(request)
        }

        return chain.proceed(builder.build())
    }
}