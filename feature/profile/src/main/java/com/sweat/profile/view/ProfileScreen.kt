package com.sweat.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.common.utill.decodeBase64Image
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.AddFriendIcon
import com.sweat.design_system.icon.CheckIcon
import com.sweat.design_system.icon.SettingIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.profile.component.ChatListItem
import com.sweat.profile.component.ProfileTopAppBar
import com.sweat.profile.component.bottomSheet.AddFriendBottomSheet
import com.sweat.profile.component.bottomSheet.SettingsBottomSheet
import com.sweat.profile.viewModel.ProfileIntent
import com.sweat.profile.viewModel.ProfileScreenSideEffect
import com.sweat.profile.viewModel.ProfileScreenState
import com.sweat.profile.viewModel.ProfileViewModel
import com.sweat.ui.DevicePreviews

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAddFriendWithQR: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToMyQR: () -> Unit,
    navigateToChat: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProfileIntent.InitMyFriend)
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileScreenSideEffect.LaunchImagePicker -> {}
                ProfileScreenSideEffect.ShowSecessionPopup -> TODO()
                ProfileScreenSideEffect.NavigateToAddFriendWithQR -> navigateToAddFriendWithQR()
                is ProfileScreenSideEffect.NavigateToChat -> navigateToChat(sideEffect.id)
                ProfileScreenSideEffect.NavigateToLogin -> navigateToLogin()
                ProfileScreenSideEffect.NavigateToMyQR -> navigateToMyQR()
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()


    ProfileScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileScreenState,
    handleIntent: (ProfileIntent) -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState()

    SSBAndroidTheme { color, _ ->
        if (state.isShowSettingBottomSheet) {
            ModalBottomSheet(
                containerColor = color.white,
                sheetState = bottomSheetState,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                onDismissRequest = { handleIntent(ProfileIntent.HideBottomSheet) },
            ) {
                SettingsBottomSheet(
                    onProfileEditClick = {
                        handleIntent(ProfileIntent.StartEditProfile)
                        handleIntent(ProfileIntent.HideBottomSheet)
                    },
                    onLeaveClick = { },
                    onLogoutClick = { handleIntent(ProfileIntent.Logout) },
                )
            }
        }

        if (state.isShowAddFriendBottomSheet) {
            ModalBottomSheet(
                containerColor = color.white,
                sheetState = bottomSheetState,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                onDismissRequest = { handleIntent(ProfileIntent.HideBottomSheet) },
            ) {
                AddFriendBottomSheet(
                    onClickMyAddFriendWithQr = { handleIntent(ProfileIntent.AddFriendWithQR) },
                    onClickMyQr = { handleIntent(ProfileIntent.ShowMyQR) },
                )
            }
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ProfileTopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    startText = "프로필",
                    endIcon = {
                        if (state.isProfileEditing) {
                            CheckIcon(
                                modifier = Modifier.clickableSingle { handleIntent(ProfileIntent.EndEditProfile) }
                            )
                        } else {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    24.dp,
                                    Alignment.CenterHorizontally
                                ),
                                verticalAlignment = Alignment.Top,
                            ) {
                                AddFriendIcon(modifier = Modifier.clickableSingle {
                                    handleIntent(ProfileIntent.AddFriend)
                                })
                                SettingIcon(modifier = Modifier.clickableSingle {
                                    handleIntent(ProfileIntent.Setting)
                                })
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        painter = getProfileImage(state.image),
                        contentDescription = "profileImage",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(60.dp),
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                    ) {
                        Text(
                            text = state.myName,
                            style = SSBTypography.subTitle,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        BasicTextField(
                            value = state.myIntro,
                            onValueChange = {
                                handleIntent(ProfileIntent.SetMyIntro(it))
                            },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            cursorBrush = SolidColor(Color.Black),
                            decorationBox = { innerTextField ->
                                if (state.myIntro.isEmpty()) {
                                    Text("한 줄 소개를 적어주세요", color = SSBColor.gray200)
                                }
                                innerTextField()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .background(Color.Transparent, shape = RectangleShape)
                        )
                        Text(
                            text = state.myIntro,
                            style = SSBTypography.bodySmall,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                        )
                    }
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.chatList) { state ->
                        ChatListItem(
                            state = state,
                            onClick = { id -> handleIntent(ProfileIntent.StartChat(id.toString())) },
                        )
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        state = ProfileScreenState.getInitialState(),
        handleIntent = { _ -> },
    )
}

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
