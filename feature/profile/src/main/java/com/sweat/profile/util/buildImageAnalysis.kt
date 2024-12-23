package com.sweat.profile.util

import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat

/**
 * QR 코드 분석을 위한 ImageAnalysis 객체를 생성하는 함수.
 *
 * @param context 컨텍스트 객체, CameraX 작업과 Executor 설정에 사용.
 * @param onQrcodeScanned QR 코드 스캔 결과를 처리하는 콜백 함수.
 * @return ImageAnalysis 객체.
 */
internal fun buildImageAnalysis(
    context: android.content.Context,
    onQrcodeScanned: (String?) -> Unit
): ImageAnalysis {
    return ImageAnalysis.Builder()
        // 백프레셔 전략을 설정. STRATEGY_KEEP_ONLY_LATEST는 가장 최신 프레임만 유지.
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build().apply {
            // 분석기를 설정. ContextCompat.getMainExecutor를 사용해 UI 스레드에서 실행.
            setAnalyzer(ContextCompat.getMainExecutor(context), QrcodeAnalyzer(onQrcodeScanned))
        }
}
