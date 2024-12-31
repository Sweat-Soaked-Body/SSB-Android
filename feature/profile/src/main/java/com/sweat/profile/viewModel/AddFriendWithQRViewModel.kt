package com.sweat.profile.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.common.exception.BadRequestException
import com.sweat.domain.friend.FriendAddUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
            is AddFriendWithQRIntent.SetFriendName -> setState { copy(friendName = intent.name) }
        }
    }

    private fun addFriend(name: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true

        viewModelScope.launch {
            try {
                friendAddUseCase(name)
                    .onSuccess {
                        it.collect {} // 실제 데이터 스트림 처리
                        setState { copy(isFinishAddFriend = true) }
                        delay(5000)
                        postSideEffect(AddFriendWithQRScreenSideEffect.NavigateToProfile)
                    }
            } catch (e: BadRequestException) {
                setState { copy(isFinishAddFriend = true) }
                delay(5000)
                postSideEffect(AddFriendWithQRScreenSideEffect.NavigateToProfile)
            } catch (e: Exception) {
                setState { copy(isFinishAddFriend = true) }
                delay(5000)
                postSideEffect(AddFriendWithQRScreenSideEffect.NavigateToProfile)
            } finally {
                isRequestInProgress = false
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
    data class SetFriendName(val name: String) : AddFriendWithQRIntent()
}
