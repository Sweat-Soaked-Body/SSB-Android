package com.sweat.profile.viewModel

import com.sweat.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendQrGenerateViewModel @Inject constructor() :
    BaseViewModel<FriendQrGenerateScreenState, FriendQrGenerateScreenSideEffect, FriendQrGenerateIntent>(
        FriendQrGenerateScreenState.getInitialState()
    ) {
    override fun handleIntent(intent: FriendQrGenerateIntent) {
        when (intent) {
            is FriendQrGenerateIntent.SetMyName -> setState { copy(myName = intent.name) }
        }
    }
}

data class FriendQrGenerateScreenState(
    val myName: String,
) {
    companion object {
        // State의 초기값을 넣어주기위해 필수로 구현해야하는 함수
        fun getInitialState() = FriendQrGenerateScreenState(
            myName = "",
        )
    }
}

sealed class FriendQrGenerateScreenSideEffect {
}

sealed class FriendQrGenerateIntent {
    data class SetMyName(val name: String) : FriendQrGenerateIntent()
}
