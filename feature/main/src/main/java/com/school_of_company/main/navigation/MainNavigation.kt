package com.school_of_company.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.school_of_company.main.view.MainRoute

const val homeRoute = "homeRoute"

fun NavController.navigateToHomeRoute(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeRoute(
    navigateToFood: () -> Unit
) {
    composable(homeRoute) {
        MainRoute(
            navigateToFood = navigateToFood
        )
    }
}