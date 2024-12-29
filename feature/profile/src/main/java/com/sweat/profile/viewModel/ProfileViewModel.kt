package com.sweat.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.friend.FriendCheckUseCase
import com.sweat.model.friend.FriendModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val friendCheckUseCase: FriendCheckUseCase,
) : BaseViewModel<ProfileScreenState, ProfileScreenSideEffect, ProfileIntent>(ProfileScreenState.getInitialState()) {

    override fun handleIntent(intent: ProfileIntent) {
        super.handleIntent(intent)
        when (intent) {
            ProfileIntent.AddFriendWithQR -> postSideEffect(ProfileScreenSideEffect.NavigateToAddFriendWithQR)
            ProfileIntent.AddProfilePicture -> postSideEffect(ProfileScreenSideEffect.LaunchImagePicker(200))
            ProfileIntent.StartEditProfile -> setState { copy(isProfileEditing = true) }
            ProfileIntent.EndEditProfile -> postProfileEdit()
            ProfileIntent.Logout -> logout()
            ProfileIntent.Secession -> postSideEffect(ProfileScreenSideEffect.ShowSecessionPopup)
            ProfileIntent.ShowMyQR -> postSideEffect(ProfileScreenSideEffect.NavigateToMyQR)
            ProfileIntent.HideBottomSheet -> setState {
                copy(
                    isShowAddFriendBottomSheet = false,
                    isShowSettingBottomSheet = false,
                )
            }

            ProfileIntent.Setting -> setState { copy(isShowSettingBottomSheet = true) }
            ProfileIntent.AddFriend -> setState { copy(isShowAddFriendBottomSheet = true) }
            is ProfileIntent.StartChat -> postSideEffect(ProfileScreenSideEffect.NavigateToChat(id = intent.id))
            is ProfileIntent.SetMyIntro -> setState { copy(myIntro = intent.state) }
            is ProfileIntent.SetProfileImage -> setState { copy(image = intent.image) }
            ProfileIntent.InitMyFriend -> loadChatList()
        }
    }

    private fun loadChatList() {
        viewModelScope.launch {
            friendCheckUseCase()
                .onSuccess {
                    it.collect {
                        setState { copy(chatList = it.toPersistentList()) }
                    }
                }
        }
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
    val chatList: ImmutableList<FriendModel>,
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

sealed class ProfileScreenSideEffect {
    object ShowSecessionPopup : ProfileScreenSideEffect()
    data class LaunchImagePicker(val requestCode: Int) : ProfileScreenSideEffect()
    object NavigateToLogin : ProfileScreenSideEffect()
    object NavigateToMyQR : ProfileScreenSideEffect()
    object NavigateToAddFriendWithQR : ProfileScreenSideEffect()
    data class NavigateToChat(val id: String) : ProfileScreenSideEffect()
}


sealed class ProfileIntent {
    object InitMyFriend : ProfileIntent()
    object Setting : ProfileIntent()
    object AddFriend : ProfileIntent()
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
    object AddFriendWithQR : ProfileIntent()
}