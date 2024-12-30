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

<<<<<<< Updated upstream
fun NavGraphBuilder.mainRoute(
    navigateToFood: () -> Unit
) {
    composable(mainRoute) {
        MainRoute(
            navigateToFood = navigateToFood
        )
=======
fun NavGraphBuilder.homeRoute(

) {
    composable(homeRoute) {
        MainRoute()
>>>>>>> Stashed changes
    }
}