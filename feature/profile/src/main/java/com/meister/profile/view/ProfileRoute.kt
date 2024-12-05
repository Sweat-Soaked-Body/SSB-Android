package com.meister.profile.view

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.meister.profile.viewModel.ChatListItemState
import com.meister.profile.viewModel.ProfileIntent
import com.meister.profile.viewModel.ProfileScreenSideEffect
import com.meister.profile.viewModel.ProfileScreenState
import com.meister.profile.viewModel.ProfileViewModel
import com.sweat.common.utill.decodeBase64Image
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.AddFriendIcon
import com.sweat.design_system.icon.CheckIcon
import com.sweat.design_system.icon.SettingIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.ui.DevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAddFriendWithQR: () -> Unit,
    navigateToAddFriendWithNFC: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToMyQR: () -> Unit,
    navigateToChat: (String) -> Unit,
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
                ProfileScreenSideEffect.NavigateToAddFriendWithNFC -> navigateToAddFriendWithNFC()
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
        handleIntent = viewModel::handleIntent
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileScreenState,
    handleIntent: (ProfileIntent) -> Unit,
) {
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
                                Text("한 줄 소개를 적어주세요", color = Color.Gray)
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
                    ChatListItem(state = state)
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


@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    state: ChatListItemState,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier.fillMaxWidth()
    ) {
        Row {
            Image(
                painter = getProfileImage(state.image),
                contentDescription = "chat partner profile",
                modifier = Modifier
                    .padding(8.dp)
                    .size(45.dp),
            )
            Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 5.dp)) {
                Text(
                    text = state.name,
                    style = SSBTypography.bodyMedium,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = state.message,
                    style = SSBTypography.label,
                    fontWeight = FontWeight(400),
                    color = if (state.isReadMessage) Color(0xFF000000)
                    else SSBColor.gray600
                )
            }
        }
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.date,
                style = SSBTypography.label,
                fontWeight = FontWeight(400),
                color = SSBColor.gray500,
                textAlign = TextAlign.Right,
            )
        }
    }
}

@Composable
fun ProfileActionItem(
    icon: @Composable () -> Unit,
    title: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        icon()
        Text(
            text = title,
            style = SSBTypography.bodySmall,
            fontWeight = FontWeight(400),
            color = textColor
        )
    }
}

@Composable
fun AddFriendBottomSheet(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(31.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
    ) {
        ProfileActionItem(
            icon = { PencilIcon() },
            title = "프로필 수정",
            textColor = SSBColor.gray600
        )
        ProfileActionItem(
            icon = { OutIcon() },
            title = "로그 아웃",
            textColor = SSBColor.gray600
        )
        ProfileActionItem(
            icon = { TrashIcon() },
            title = "프로필 삭제",
            textColor = SSBColor.error
        )
    }
}

@Composable
fun SettingsBottomSheet(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(31.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
    ) {
        ProfileActionItem(
            icon = { OutIcon() }, // TODO: 아이콘 변경
            title = "내 QR코드 보기",
            textColor = SSBColor.gray600
        )
        ProfileActionItem(
            icon = { OutIcon() },// TODO: 아이콘 변경
            title = "QR 코드로 친구 추가",
            textColor = SSBColor.gray600
        )
        ProfileActionItem(
            icon = { TrashIcon() },// TODO: 아이콘 변경
            title = "NFC로 친구 추가",
            textColor = SSBColor.gray600
        )
    }
}
