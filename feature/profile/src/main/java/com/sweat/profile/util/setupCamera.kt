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
    onQrcodeScanned: (String?) -> Unit,
) {
    val context = previewView.context
    // CameraX의 카메라 프로바이더를 비동기로 가져옴
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    // 카메라 프로바이더 준비 완료 리스너 등록
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        try {
            // 기본 카메라를 후면 카메라로 선택
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // Preview 설정
            val preview = buildPreview(previewView)

            // ImageAnalysis 설정 (QR 코드 분석)
            val imageAnalysis = buildImageAnalysis(context, onQrcodeScanned)

            // 기존 카메라 바인딩 해제 후 새로 바인딩
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner, // 라이프사이클에 따라 카메라 작동
                cameraSelector, // 선택된 카메라 (후면 카메라)
                preview,        // 화면에 표시할 프리뷰
                imageAnalysis   // QR 코드 분석을 위한 이미지 분석기
            )
        } catch (exc: Exception) {
            // 바인딩 실패 시 에러 로그 출력
            Log.e("QrcodeScanner", "Use case binding failed: ${exc.message}", exc)
        }
    }, ContextCompat.getMainExecutor(context)) // UI 스레드에서 실행
}
