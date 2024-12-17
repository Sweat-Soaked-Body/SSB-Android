package com.meister.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.meister.profile.view.ProfileRoute

const val profileRoute = "profileRoute"

fun NavController.navigateToProfileRoute(navOptions: NavOptions? = null) {
    this.navigate(profileRoute, navOptions)
}

fun NavGraphBuilder.profileRoute(
    navigateToAddFriendWithQR: () -> Unit,
    navigateToAddFriendWithNFC: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToMyQR: () -> Unit,
    navigateToChat: (String) -> Unit,
) {
    composable(profileRoute) {
        ProfileRoute(
            navigateToAddFriendWithQR = navigateToAddFriendWithQR,
            navigateToAddFriendWithNFC = navigateToAddFriendWithNFC,
            navigateToLogin = navigateToLogin,
            navigateToMyQR = navigateToMyQR,
            navigateToChat = navigateToChat,
        )
    }
}