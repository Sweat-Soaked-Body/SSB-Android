package com.sweat.profile.util

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

/**
 * QR 코드 분석을 수행하는 Analyzer 클래스.
 * CameraX의 ImageAnalysis.Analyzer를 구현하여 QR 코드를 스캔하고 결과를 처리합니다.
 *
 * @param onQrcodeScanned QR 코드 데이터를 처리할 콜백 함수.
 */
class QrcodeAnalyzer(
    private val onQrcodeScanned: (String?) -> Unit
) : ImageAnalysis.Analyzer {

    // QR 코드 스캐너 클라이언트 초기화
    private val scanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            // 스캔할 바코드 포맷: QR 코드로 설정
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        // ImageProxy에서 MediaImage를 가져옴. null인 경우 작업 중단.
        val mediaImage = imageProxy.image ?: run {
            imageProxy.close()
            return
        }

        // MediaImage를 ML Kit에서 사용할 수 있는 InputImage로 변환
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        // QR 코드 스캔 시작
        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                // 스캔된 바코드 중 displayValue가 있는 데이터를 추출
                val qrCodeValues = barcodes.mapNotNull { it.displayValue }
                if (qrCodeValues.isNotEmpty()) {
                    // 콜백 함수로 QR 코드 데이터 전달
                    onQrcodeScanned(qrCodeValues.joinToString(", "))
                }
            }
            .addOnFailureListener {
                // QR 코드 스캔 실패 시 로그 출력
                Log.d("QrcodeScanner", "QR code scanning failed", it)
            }
            .addOnCompleteListener {
                // 스캔 작업이 완료되면 ImageProxy를 닫아 자원 해제
                imageProxy.close()
            }
    }
}
