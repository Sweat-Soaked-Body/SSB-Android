package com.sweat.profile.viewModel

import com.sweat.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : BaseViewModel<ProfileScreenState, ProfileScreenSideEffect, ProfileIntent>(ProfileScreenState.getInitialState()) {
    override fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.AddFriendWithNFC -> postSideEffect(ProfileScreenSideEffect.NavigateToAddFriendWithNFC)
            ProfileIntent.AddFriendWithQR -> postSideEffect(ProfileScreenSideEffect.NavigateToAddFriendWithQR)
            ProfileIntent.AddProfilePicture -> postSideEffect(ProfileScreenSideEffect.LaunchImagePicker(200))
            ProfileIntent.StartEditProfile -> { setState { copy(isProfileEditing = true) } }
            ProfileIntent.EndEditProfile -> postProfileEdit()
            ProfileIntent.Logout -> logout()
            ProfileIntent.Secession -> postSideEffect(ProfileScreenSideEffect.ShowSecessionPopup)
            ProfileIntent.ShowMyQR -> postSideEffect(ProfileScreenSideEffect.NavigateToMyQR)
            ProfileIntent.HideBottomSheet -> postSideEffect(ProfileScreenSideEffect.HideBottomSheet)
            ProfileIntent.Setting -> {
                setState { copy(currentBottomSheetType = BottomSheetType.Settings) }
                postSideEffect(ProfileScreenSideEffect.ShowBottomSheet)
            }
            ProfileIntent.AddFriend -> {
                setState { copy(currentBottomSheetType = BottomSheetType.AddFriend) }
                postSideEffect(ProfileScreenSideEffect.ShowBottomSheet)
            }
            is ProfileIntent.StartChat -> postSideEffect(ProfileScreenSideEffect.NavigateToChat(id = intent.id))
            is ProfileIntent.SetMyIntro -> setState { copy(myIntro = intent.state) }
            is ProfileIntent.SetProfileImage -> setState { copy(image = intent.image) }
        }
    }

    private fun loadChatList() {

    }

    private fun loadProfileData() {

    }

    private fun postProfileEdit() {
        // TODO: 통신이 성공하면 실행시키도록 변경
        setState { copy(isProfileEditing = false) }
    }

    private fun logout() {
        // TODO: 토큰들 지우기
        postSideEffect(ProfileScreenSideEffect.NavigateToLogin)
    }
}

data class ProfileScreenState(
    val myName: String,
    val myIntro: String,
    val image: String,
    val isProfileEditing: Boolean,
    val isShowSettingBottomSheet: Boolean,
    val isShowAddFriendBottomSheet: Boolean,
    val chatList: ImmutableList<ChatListItemState>,
) {
    companion object {
        // State의 초기값을 넣어주기위해 필수로 구현해야하는 함수
        fun getInitialState() = ProfileScreenState(
            myName = "",
            myIntro = "",
            image = "",
            isProfileEditing = false,
            isShowSettingBottomSheet = false,
            isShowAddFriendBottomSheet = false,
            chatList = persistentListOf(),
        )
    }
}

data class ChatListItemState(
    val name: String,
    val message: String,
    val date: String,
    val image: String,
    val isReadMessage: Boolean,
)

enum class BottomSheetType {
    None,
    AddFriend,
    Settings
}

sealed class ProfileScreenSideEffect {
    object ShowSecessionPopup : ProfileScreenSideEffect()
    data class LaunchImagePicker(val requestCode: Int) : ProfileScreenSideEffect()
    object ShowBottomSheet : ProfileScreenSideEffect()
    object HideBottomSheet : ProfileScreenSideEffect()
    object NavigateToLogin : ProfileScreenSideEffect()
    object NavigateToMyQR : ProfileScreenSideEffect()
    object NavigateToAddFriendWithQR : ProfileScreenSideEffect()
    object NavigateToAddFriendWithNFC : ProfileScreenSideEffect()
    data class NavigateToChat(val id: String) : ProfileScreenSideEffect()
}


sealed class ProfileIntent {
    object Setting : ProfileIntent()
    object HideBottomSheet : ProfileIntent()
    data class StartChat(val id: String) : ProfileIntent()
    data class SetMyIntro(val state: String) : ProfileIntent()
    data class SetProfileImage(val image: String) : ProfileIntent()
    object StartEditProfile : ProfileIntent()
    object EndEditProfile : ProfileIntent()
    object Logout : ProfileIntent()
    object Secession : ProfileIntent()
    object ShowMyQR : ProfileIntent()
    object AddProfilePicture : ProfileIntent()
    object AddFriend : ProfileIntent()
    object AddFriendWithQR : ProfileIntent()
    object AddFriendWithNFC : ProfileIntent()
}