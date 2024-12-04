package com.meister.profile.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.meister.profile.viewModel.ProfileIntent
import com.meister.profile.viewModel.ProfileScreenSideEffect
import com.meister.profile.viewModel.ProfileScreenState
import com.meister.profile.viewModel.ProfileViewModel
import kotlin.reflect.KFunction1

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                ProfileScreenSideEffect.LaunchAddFriendBottomSheet -> TODO()
                is ProfileScreenSideEffect.LaunchImagePicker -> TODO()
                ProfileScreenSideEffect.LaunchSettingBottomSheet -> TODO()
                ProfileScreenSideEffect.NavigateToAddFriendWithNFC -> TODO()
                ProfileScreenSideEffect.NavigateToAddFriendWithQR -> TODO()
                is ProfileScreenSideEffect.NavigateToChat -> TODO()
                ProfileScreenSideEffect.NavigateToLogin -> TODO()
                ProfileScreenSideEffect.NavigateToMyQR -> TODO()
                ProfileScreenSideEffect.ShowSecessionPopup -> TODO()
            }
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileScreenState,
    handleIntent: KFunction1<ProfileIntent, Unit>
) {

@Composable
fun getProfileImage(base64Image: String): Painter {
    return if (base64Image.isEmpty()) {
        painterResource(com.sweat.design_system.R.drawable.profile_square)
    } else {
        decodeBase64Image(base64Image)?.asImageBitmap()?.let {
            BitmapPainter(it)
        } ?: painterResource(com.sweat.design_system.R.drawable.profile_square)
    }
}

@Composable
fun ProfileTopAppBar(
    modifier: Modifier = Modifier,
    startText: String,
    endIcon: @Composable () -> Unit
) {
    Row(
        modifier = modifier.padding(
            vertical = 13.dp,
            horizontal = 24.dp
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = startText,
            style = SSBTypography.titleSmall,
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
        endIcon()
    }
}
