package com.sweat.exercise.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.exercise.exerciseRoute

const val addExerciseRoute = "addExerciseRoute"

fun NavController.navigateToAddExerciseRoute(navOptions: NavOptions? = null){
    this.navigate(addExerciseRoute, navOptions)
}

fun NavGraphBuilder.addExerciseRoute(
    navigateToExerciseScreen: () -> Unit,
    popUpBackStack: () -> Unit,
){
    composable(exerciseRoute){
        AddExerciseRoute(
            navigateToExerciseScreen = navigateToExerciseScreen,
            popUpBackStack = popUpBackStack,
        )
    }
}