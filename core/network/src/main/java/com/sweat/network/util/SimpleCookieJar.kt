package com.sweat.network.util

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class SimpleCookieJar : CookieJar{
    private val cookieStore = mutableMapOf<String,MutableList<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies:List<Cookie>) {
        cookieStore[url.host]?.clear()
        cookieStore[url.host] = cookies.toMutableList()
    }

    override fun loadForRequest(url: HttpUrl):List<Cookie> {
        return cookieStore[url.host] ?: emptyList()
    }
}