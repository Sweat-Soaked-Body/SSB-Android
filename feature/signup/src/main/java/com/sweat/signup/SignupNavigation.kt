package com.sweat.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.signup.view.SignupRoute

const val SignupRoute = "SignupRoute"

fun NavController.navigateToSignupRoute(navOptions: NavOptions? = null) {
    this.navigate(SignupRoute, navOptions)
}

fun NavGraphBuilder.signupRoute(
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(SignupRoute) {
        SignupRoute(
            navigateToMain = navigateToMain,
            navigateToLogin = navigateToLogin
        )
    }
}