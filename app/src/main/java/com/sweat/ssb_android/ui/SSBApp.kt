package com.sweat.ssb_android.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sweat.ssb_android.navigation.TopLevelDestination

@Composable
fun SSBApp(
    windowSizeClass: WindowSizeClass,
    appState: SSBAppState = rememberSSBAppState(windowSizeClass = windowSizeClass)
) {
    val isBottomBarVisible = remember { mutableStateOf(true) }
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val topLevelDestinationRoute = arrayOf(
        // add route
        TopLevelDestination.Home // example code
    )

    navBackStackEntry?.destination?.route?.let {
        isBottomBarVisible.value = topLevelDestinationRoute.contains(TopLevelDestination.Home) // contains() <- example code
    }

    // Scaffold Code
}

@Composable
fun SSBBottomBar(
    destination: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    // BottomBar Code
}