package com.sweat.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.signup.view.SignupRoute

const val signupRoute = "signupRoute"

fun NavController.navigateToSignupRoute(navOptions: NavOptions? = null) {
    this.navigate(signupRoute, navOptions)
}

fun NavGraphBuilder.signupRoute(
    navigateToMain: () -> Unit,
) {
    composable(signupRoute) {
        SignupRoute(
            navigateToMain = navigateToMain,
        )
    }
}