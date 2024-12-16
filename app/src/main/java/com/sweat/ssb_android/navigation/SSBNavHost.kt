package com.sweat.ssb_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.meister.profile.profileRoute
import com.sweat.signup.signupRoute

@Composable
fun SSBNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
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
    }
}
