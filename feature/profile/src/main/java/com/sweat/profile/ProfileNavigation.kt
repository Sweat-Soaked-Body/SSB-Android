package com.sweat.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.profile.view.AddFriendWithQRRoute
import com.sweat.profile.view.ProfileRoute

const val profileRoute = "profileRoute"
const val addFriendWithQRRoute = "addFriendWithQRRoute"

fun NavController.navigateToProfileRoute(navOptions: NavOptions? = null) {
    this.navigate(profileRoute, navOptions)
}

fun NavController.navigateToAddFriendWithQR(navOptions: NavOptions? = null) {
    this.navigate(addFriendWithQRRoute, navOptions)
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

fun NavGraphBuilder.addFriendWithQRRoute(
    navigateToProfile: () -> Unit,
    popUpBackStack: () -> Unit,
) {
    composable(addFriendWithQRRoute) {
        AddFriendWithQRRoute(
            navigateToProfile = navigateToProfile,
            popupBackStack = popUpBackStack,
        )
    }
}