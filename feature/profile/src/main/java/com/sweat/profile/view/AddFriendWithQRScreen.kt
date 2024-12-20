package com.sweat.profile.view

import android.Manifest
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.profile.R
import com.sweat.profile.component.AddFriendSuccessCard
import com.sweat.profile.util.setupCamera
import com.sweat.profile.viewModel.AddFriendWithQRIntent
import com.sweat.profile.viewModel.AddFriendWithQRScreenSideEffect
import com.sweat.profile.viewModel.AddFriendWithQRScreenState
import com.sweat.profile.viewModel.AddFriendWithQRViewModel

@Composable
fun AddFriendWithQRRoute(
    modifier: Modifier = Modifier,
    viewModel: AddFriendWithQRViewModel = hiltViewModel(),
    popupBackStack: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = context as? LifecycleOwner
        ?: throw IllegalStateException("Context is not a LifecycleOwner")

    // 카메라 권한 상태 관리
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    // 권한 요청 처리
    LaunchedEffect("cameraPermission") {
        if (!cameraPermissionState.status.isGranted && !cameraPermissionState.status.shouldShowRationale) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                AddFriendWithQRScreenSideEffect.NavigateToProfile -> navigateToProfile()
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    AddFriendWithQRScreen(
        modifier = modifier,
        state = state,
        popupBackStack = popupBackStack,
        handleIntent = viewModel::handleIntent,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddFriendWithQRScreen(
    modifier: Modifier = Modifier,
    state: AddFriendWithQRScreenState,
    popupBackStack: () -> Unit,
    handleIntent: (AddFriendWithQRIntent) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = context as? LifecycleOwner
        ?: throw IllegalStateException("Context is not a LifecycleOwner")

    // 카메라 권한 상태 관리
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    // 권한 요청 처리
    LaunchedEffect("cameraPermission") {
        if (!cameraPermissionState.status.isGranted && !cameraPermissionState.status.shouldShowRationale) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (state.isFinishAddFriend) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                ChevronLeftIcon(modifier = Modifier.clickableSingle(onClick = popupBackStack))
            }
            Spacer(modifier = Modifier.height(34.dp))
            AddFriendSuccessCard(friendName = state.friendName, context = context)
        }
    } else if (
        cameraPermissionState.status.isGranted
        && !state.isFinishAddFriend
    ) {
        // 권한이 허용된 경우에만 AndroidView 실행
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            // QR 스캐너 뷰
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        post {
                            setupCamera(
                                previewView = this,
                                lifecycleOwner = lifecycleOwner,
                                onQrcodeScanned = {
                                    handleIntent(
                                        AddFriendWithQRIntent.AddFriend(
                                            it ?: ""
                                        )
                                    )
                                },
                            )
                        }
                    }
                }
            )

            // Compose로 Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .align(Alignment.TopStart),
                horizontalArrangement = Arrangement.Start,
            ) {
                ChevronLeftIcon(modifier = Modifier.clickableSingle(onClick = popupBackStack))
            }

            // QR 가이드 이미지
            Icon(
                painter = painterResource(R.drawable.qr_guide),
                contentDescription = "QR Guide",
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(1f)
            )
        }
    }
}

@Preview
@Composable
fun AddFriendWithQRScreenPreview() {
    AddFriendWithQRScreen(
        handleIntent = { _ -> },
        popupBackStack = { },
        state = AddFriendWithQRScreenState.getInitialState()
    )
}