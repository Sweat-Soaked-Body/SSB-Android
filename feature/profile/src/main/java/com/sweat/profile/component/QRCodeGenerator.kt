package com.sweat.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.sweat.profile.util.generateQRCodeBitmap

@Composable
fun QRCodeGenerator(content: String, sizeDp: Int = 240) {
    val sizePx = (sizeDp * androidx.compose.ui.platform.LocalDensity.current.density).toInt()

    val qrBitmap = generateQRCodeBitmap(content, sizePx)

    qrBitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "QR Code",
            modifier = Modifier.size(sizeDp.dp) // 크기를 240x240 dp로 설정
        )
    }
}