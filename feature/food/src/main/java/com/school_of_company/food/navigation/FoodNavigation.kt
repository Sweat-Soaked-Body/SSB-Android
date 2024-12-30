package com.school_of_company.food.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.school_of_company.food.view.AddFoodRoute
import com.school_of_company.food.view.SearchFoodRoute

const val addFoodRoute = "add_food_route"
const val searchFood = "search_food"

fun NavController.navigateToFoodScreen(navOptions: NavOptions? = null) {
    this.navigate(addFoodRoute, navOptions)
}

fun NavController.navigateToSearchFoodScreen(navOptions: NavOptions? = null) {
    this.navigate(searchFood, navOptions)
}

fun NavGraphBuilder.addFoodRoute(
    popUpBackStack: () -> Unit,
    navigateToFoodSearch: () -> Unit
) {
    composable(addFoodRoute) {
        AddFoodRoute(
            popUpBackStack = popUpBackStack,
            navigateToFoodSearch = navigateToFoodSearch
        )
    }
}

fun NavGraphBuilder.searchFoodRoute(
    popUpBackStack: () -> Unit
) {
    composable(searchFood) {
        SearchFoodRoute(
            popUpBackStack = popUpBackStack
        )
    }
}