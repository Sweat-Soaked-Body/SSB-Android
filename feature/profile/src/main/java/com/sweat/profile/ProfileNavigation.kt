package com.sweat.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.profile.view.ProfileRoute

const val ProfileRoute = "ProfileRoute"

fun NavController.navigateToProfileRoute(navOptions: NavOptions? = null) {
    this.navigate(ProfileRoute, navOptions)
}

fun NavGraphBuilder.profileRoute(
    navigateToAddFriendWithQR: () -> Unit,
    navigateToAddFriendWithNFC: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToMyQR: () -> Unit,
    navigateToChat: (String) -> Unit,
) {
    composable(ProfileRoute) {
        ProfileRoute(
            navigateToAddFriendWithQR = navigateToAddFriendWithQR,
            navigateToAddFriendWithNFC = navigateToAddFriendWithNFC,
            navigateToLogin = navigateToLogin,
            navigateToMyQR = navigateToMyQR,
            navigateToChat = navigateToChat,
        )
    }
}