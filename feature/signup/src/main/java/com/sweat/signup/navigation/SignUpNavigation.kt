package com.sweat.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.signup.view.SignUpRoute

const val signUpRoute = "sign_up_route"

fun NavController.navigateToSignupRoute(navOptions: NavOptions? = null) {
    this.navigate(signUpRoute, navOptions)
}

fun NavGraphBuilder.signupRoute(
    navigateToMain: () -> Unit,
    popUpBackStack: () -> Unit,
    makeErrorToast: (throwable: Throwable?, message: Int?) -> Unit
) {
    composable(signUpRoute) {
        SignUpRoute(
            navigateToMain = navigateToMain,
            popUpBackStack = popUpBackStack,
            makeErrorToast = makeErrorToast
        )
    }
}