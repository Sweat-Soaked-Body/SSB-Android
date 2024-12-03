package com.sweat.common.utill

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun decodeBase64Image(encodedString: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(encodedString, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        null // 변환 실패 시 null 반환
    }
}
