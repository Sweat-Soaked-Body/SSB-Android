package com.sweat.exercise

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.exercise.view.ExerciseRoute

const val exerciseRoute = "exerciseRoute"

fun NavController.navigateToExerciseRoute(navOptions: NavOptions? = null){
    this.navigate(exerciseRoute, navOptions)
}

fun NavGraphBuilder.exerciseRoute(
    navigateToAddExerciseScreen: () -> Unit,
    popUpBackStack: () -> Unit,
){
    composable(exerciseRoute){
        ExerciseRoute(
            navigateToAddExerciseScreen = navigateToAddExerciseScreen,
            popUpBackStack = popUpBackStack,
        )
    }
}