package com.sweat.ssb_android.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.sweat.login.loginRoute
import com.sweat.ssb_android.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberSSBAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
): SSBAppState {
    return remember(
        bottomSheetState,
        navController,
        coroutineScope,
        windowSizeClass
    ) {
        SSBAppState(
            bottomSheetState = bottomSheetState,
            navController = navController,
            windowSizeClass = windowSizeClass,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class SSBAppState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val bottomSheetState: SheetState,
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
                TopLevelDestination.Exercise -> TODO() // navController.navigateToExercise(topLevelNavOptions)
                TopLevelDestination.Home -> TODO() // navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.Profile -> TODO() // navController.navigateToProfile(topLevelNavOptions)
            }
        }
    }
}

fun NavController.navigateWithPopUpToLogin() {
    this.navigate(loginRoute) {
        popUpTo(loginRoute) { inclusive = false }
    }
}