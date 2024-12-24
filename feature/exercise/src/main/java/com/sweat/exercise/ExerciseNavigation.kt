package com.sweat.exercise

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sweat.exercise.view.AddExerciseRoute
import com.sweat.exercise.view.ExerciseRoute

const val exerciseRoute = "exerciseRoute"
const val addExerciseRoute = "addExerciseRoute"

fun NavController.navigateToExerciseRoute(navOptions: NavOptions? = null){
    this.navigate(exerciseRoute, navOptions)
}

fun NavController.navigateToAddExerciseRoute(navOptions: NavOptions? = null){
    this.navigate(addExerciseRoute, navOptions)
}

fun NavGraphBuilder.exerciseRoute(
    navigateToAddExercise: () -> Unit
){
    composable(exerciseRoute){
        ExerciseRoute(
            navigateToAddExercise = navigateToAddExercise
        )
    }
}

fun NavGraphBuilder.addExerciseRoute(
    popUpBackStack: () -> Unit,
){
    composable(addExerciseRoute){
        AddExerciseRoute(
            popUpBackStack = popUpBackStack,
        )
    }
}