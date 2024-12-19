package com.sweat.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.friend.FriendAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFriendWithQRViewModel @Inject constructor(
    private val friendAddUseCase: FriendAddUseCase,
) : BaseViewModel<AddFriendWithQRScreenState, AddFriendWithQRScreenSideEffect, AddFriendWithQRIntent>(
    AddFriendWithQRScreenState.getInitialState()
) {
    private var isRequestInProgress = false

    override fun handleIntent(intent: AddFriendWithQRIntent) {
        when (intent) {
            is AddFriendWithQRIntent.AddFriend -> addFriend(intent.name)
        }
    }

    private fun addFriend(name: String) {
        // 요청 중인 경우 새로운 요청 차단
        if (isRequestInProgress) return

        // 요청 상태를 진행 중으로 설정
        isRequestInProgress = true

        viewModelScope.launch {
            friendAddUseCase(name)
                .onSuccess {
                    setState { copy(isFinishAddFriend = true) }
                         }
        }
    }
}

data class AddFriendWithQRScreenState(
    val friendName: String,
    val isFinishAddFriend: Boolean,
) {
    companion object {
        // State의 초기값을 넣어주기위해 필수로 구현해야하는 함수
        fun getInitialState() = AddFriendWithQRScreenState(
            friendName = "",
            isFinishAddFriend = false,
        )
    }
}

sealed class AddFriendWithQRScreenSideEffect {
    object NavigateToProfile : AddFriendWithQRScreenSideEffect()
}

sealed class AddFriendWithQRIntent {
    data class AddFriend(val name: String) : AddFriendWithQRIntent()
}
