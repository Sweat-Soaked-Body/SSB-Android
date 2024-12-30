package com.sweat.profile.util

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

/**
 * 카메라를 초기화하고 QR 코드 분석을 설정하는 함수.
 *
 * @param previewView 카메라 프리뷰를 표시할 PreviewView 객체.
 * @param lifecycleOwner LifecycleOwner 객체로, 카메라 라이프사이클을 관리.
 * @param onQrcodeScanned QR 코드 스캔 결과를 처리하는 콜백 함수.
 */

internal fun setupCamera(
    previewView: PreviewView,
    lifecycleOwner: LifecycleOwner,
    onQrcodeScanned: (String) -> Unit, // String 타입으로 변경, nullable 제거
) {
    val context = previewView.context
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        try {
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            val preview = buildPreview(previewView)
            val imageAnalysis = buildImageAnalysis(context) { qrCodeData ->
                // QR 코드 데이터가 유효하고 빈 문자열이 아닐 때만 콜백 실행
                qrCodeData?.takeIf { it.isNotEmpty() }?.let { onQrcodeScanned(it) }
            }

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis
            )
        } catch (exc: Exception) {
            Log.e("QrcodeScanner", "Use case binding failed: ${exc.message}", exc)
        }
    }, ContextCompat.getMainExecutor(context))
}
