package com.sweat.ssb_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sweat.login.loginRoute
import com.sweat.signup.signupRoute
import com.sweat.profile.profileRoute
import com.sweat.ssb_android.ui.SSBAppState

@Composable
fun SSBNavHost(
    modifier: Modifier = Modifier,
    appState: SSBAppState, // 네비게이션의 상태를 포함하는 앱의 상태
    startDestination: String = loginRoute,
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        profileRoute(
            navigateToAddFriendWithQR = {},
            navigateToChat = {},
            navigateToLogin = {},
            navigateToMyQR = {},
            navigateToAddFriendWithNFC = {},
        )

        signupRoute(
            navigateToMain = {},
            navigateToLogin = {}
        )

        loginRoute(
            navigateToMain = {},
            navigateToSignup = {}
        )
    }
}
