package com.sweat.exercise

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.exercise.view.AddExerciseRoute

const val addExerciseRoute = "addExerciseRoute"

fun NavController.navigateToAddExerciseRoute(navOptions: NavOptions? = null){
    this.navigate(addExerciseRoute, navOptions)
}

fun NavGraphBuilder.addExerciseRoute(
    navigateToExercise: () -> Unit,
    popUpBackStack: () -> Unit,
){
    composable(exerciseRoute){
        AddExerciseRoute(
            navigateToExercise = navigateToExercise,
            popUpBackStack = popUpBackStack,
        )
    }
}