package com.sweat.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.profile.view.AddFriendWithQRRoute
import com.sweat.profile.view.ChattingRoute
import com.sweat.profile.view.FriendQrGenerateRoute
import com.sweat.profile.view.ProfileRoute

const val profileRoute = "profileRoute"
const val addFriendWithQRRoute = "addFriendWithQRRoute"
const val friendQrGenerateRoute = "friendQrGenerateRoute"
const val chattingRoute = "chattingRoute"

fun NavController.navigateToProfileRoute(navOptions: NavOptions? = null) {
    this.navigate(profileRoute, navOptions)
}

fun NavController.navigateToAddFriendWithQR(navOptions: NavOptions? = null) {
    this.navigate(addFriendWithQRRoute, navOptions)
}

fun NavController.navigateToChattingRoute(id: String, navOptions: NavOptions? = null) {
    this.navigate("${chattingRoute}/${id}", navOptions)
}

fun NavController.navigateToFriendQrGenerate(name: String, navOptions: NavOptions? = null) {
    this.navigate("${friendQrGenerateRoute}/${name}", navOptions)
}

fun NavGraphBuilder.profileRoute(
    navigateToAddFriendWithQR: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToMyQR: (String) -> Unit,
    navigateToChat: (String) -> Unit,
) {
    composable(profileRoute) {
        ProfileRoute(
            navigateToAddFriendWithQR = navigateToAddFriendWithQR,
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

fun NavGraphBuilder.chattingRoute(
    popUpBackStack: () -> Unit,
) {
    composable("${chattingRoute}/{id}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id") ?: ""
        ChattingRoute(id = id, popUpBackStack = popUpBackStack)
    }
}

fun NavGraphBuilder.friendQrGenerateRoute(
    popUpBackStack: () -> Unit,
) {
    composable("${friendQrGenerateRoute}/{name}") { backStackEntry ->
        val name = backStackEntry.arguments?.getString("name") ?: ""
        FriendQrGenerateRoute(
            myName = name,
            popupBackStack = popUpBackStack,
        )
    }
}
