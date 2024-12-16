package com.sweat.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.login.view.LoginRoute

const val LoginRoute = "LoginRoute"

fun NavController.navigateToLoginRouteRoute(navOptions: NavOptions? = null) {
    this.navigate(LoginRoute, navOptions)
}

fun NavGraphBuilder.loginRoute(
    navigateToSignup: () -> Unit,
    navigateToMain: () -> Unit
) {
    composable(LoginRoute) {
        LoginRoute(
            navigateToSignup = navigateToSignup,
            navigateToMain = navigateToMain
        )
    }
}