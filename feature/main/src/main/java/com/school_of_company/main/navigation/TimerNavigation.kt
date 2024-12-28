package com.school_of_company.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.school_of_company.main.view.MainTimerRoute

const val timerRoute = "timerRoute"

fun NavController.navigateToTimerScreenRoute(navOptions: NavOptions? = null) {
    this.navigate(timerRoute, navOptions)
}

fun NavGraphBuilder.timerRoute(
    popUpBackStack: () -> Unit,
) {
    composable(timerRoute) {
        MainTimerRoute(
            popUpBackStack = popUpBackStack
        )
    }
}