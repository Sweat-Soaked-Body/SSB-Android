package com.sweat.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.login.view.LoginRoute

const val loginRoute = "loginRoute"

fun NavController.navigateToLoginRoute(navOptions: NavOptions? = null) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginRoute(
    navigateToSignup: () -> Unit,
    navigateToMain: () -> Unit
) {
    composable(loginRoute) {
        LoginRoute(
            navigateToSignup = navigateToSignup,
            navigateToMain = navigateToMain
        )
    }
}