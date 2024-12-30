package com.sweat.ssb_android.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.school_of_company.main.navigation.navigateToHomeRoute
import com.sweat.exercise.navigateToExerciseRoute
import com.sweat.login.loginRoute
import com.sweat.profile.navigateToProfileRoute
import com.sweat.ssb_android.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberSSBAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): SSBAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass
    ) {
        SSBAppState(
            navController = navController,
            windowSizeClass = windowSizeClass,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class SSBAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val isTopLevelDestination: Boolean
        @Composable get() = TopLevelDestination.values()
            .any { currentDestination?.route == it.routeName }

    val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigationToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: $topLevelDestination") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
            when (topLevelDestination) {
                TopLevelDestination.Exercise -> navController.navigateToExerciseRoute(topLevelNavOptions) // navController.navigateToExercise(topLevelNavOptions)
                TopLevelDestination.Home -> navController.navigateToHomeRoute(topLevelNavOptions) // navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.Profile -> navController.navigateToProfileRoute(topLevelNavOptions) // navController.navigateToProfile(topLevelNavOptions)
            }
        }
    }
}

fun NavController.navigateWithPopUpToLogin() {
    this.navigate(loginRoute) {
        popUpTo(loginRoute) { inclusive = false }
    }
}