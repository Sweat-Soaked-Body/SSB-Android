package com.sweat.profile.util

import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.view.PreviewView

/**
 * CameraX의 Preview 객체를 생성하고 설정하는 함수입니다.
 * 이 함수는 주어진 PreviewView에 카메라 프리뷰를 연결하며,
 * 해상도와 화면 비율 설정을 포함합니다.
 *
 * @param previewView 카메라 프리뷰를 표시할 PreviewView.
 * @return 설정된 Preview 객체.
 */
internal fun buildPreview(previewView: PreviewView): Preview {
    return Preview.Builder()
        .setResolutionSelector(
            // 해상도 설정: 16:9 화면 비율을 우선 적용하고,
            // 사용할 수 없는 경우 자동으로 다른 비율을 선택합니다.
            ResolutionSelector.Builder()
                .setAspectRatioStrategy(AspectRatioStrategy.RATIO_16_9_FALLBACK_AUTO_STRATEGY)
                .build()
        )
        .build().apply {
            // PreviewView의 SurfaceProvider를 설정하여 카메라 프리뷰 데이터를 표시합니다.
            setSurfaceProvider(previewView.surfaceProvider)
        }
}
