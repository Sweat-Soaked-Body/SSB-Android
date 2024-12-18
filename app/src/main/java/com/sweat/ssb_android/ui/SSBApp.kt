package com.sweat.ssb_android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sweat.design_system.component.navigationbar.SSBBottomNavigationBar
import com.sweat.design_system.component.navigationbar.SSBNavigationBarItem
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.ssb_android.navigation.SSBNavHost
import com.sweat.ssb_android.navigation.TopLevelDestination

@Composable
fun SSBApp(
    windowSizeClass: WindowSizeClass,
    appState: SSBAppState = rememberSSBAppState(windowSizeClass = windowSizeClass)
) {
    val isBottomBarVisible = remember { mutableStateOf(true) }

    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()

    val topLevelDestinationRoute = arrayOf(
        // homeRoute <- example code
        TopLevelDestination.Home // temporary code
    )

    navBackStackEntry?.destination?.route?.let {
        isBottomBarVisible.value = topLevelDestinationRoute.contains(TopLevelDestination.Home) // contains() <- example code
    }

    SSBAndroidTheme { _, _ ->
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                // BottomBar가 보여져야 하는 경우에만 표시합니다.
                if (isBottomBarVisible.value) {
                    SSBBottomBar(
                        destinations = appState.topLevelDestination, // 최상위 목적지 목록을 전달
                        onNavigateToDestination = appState::navigationToTopLevelDestination, // 네비게이션 함수
                        currentDestination = appState.currentDestination // 현재 목적지 정보
                    )
                }
            }
        ) { paddingValues ->
            // 네비게이션 호스트
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                SSBNavHost(appState = appState)
            }
        }
    }
}

@Composable
fun SSBBottomBar(
    destinations: List<TopLevelDestination>, // BottomBar에 표시될 최상위 목적지 목록
    onNavigateToDestination: (TopLevelDestination) -> Unit, // 사용자가 클릭했을 때 호출될 콜백
    currentDestination: NavDestination? // 현재 네비게이션 목적지
) {
    SSBAndroidTheme { _, typography ->
        // 커스텀 네비게이션 바 구성 요소
        SSBBottomNavigationBar {
            // 각 최상위 목적지에 대한 아이템을 생성합니다.
            destinations.forEach { destination ->
                // 현재 목적지가 선택된 상태인지 확인
                val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

                // 각 네비게이션 바 아이템을 설정합니다.
                SSBNavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        Icon(
                            painter = painterResource(id = destination.unselectedIcon),
                            contentDescription = null
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = destination.unselectedIcon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = destination.iconText,
                            style = typography.navi
                        )
                    }
                )
            }
        }
    }
}

// 현재 네비게이션 목적지가 최상위 목적지 계층에 속해 있는지 확인하는 함수
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false // 목적지의 경로에 해당 최상위 목적지가 포함되어 있는지 확인
    } ?: false