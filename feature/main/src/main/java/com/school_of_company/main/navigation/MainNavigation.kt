package com.school_of_company.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.school_of_company.main.view.MainRoute

const val mainRoute = "mainRoute"

fun NavController.navigateToMainRoute(navOptions: NavOptions? = null) {
    this.navigate(mainRoute, navOptions)
}

fun NavGraphBuilder.mainRoute(
    navigateToFood: () -> Unit
) {
    composable(mainRoute) {
        MainRoute(
            navigateToFood = navigateToFood
        )
    }
}