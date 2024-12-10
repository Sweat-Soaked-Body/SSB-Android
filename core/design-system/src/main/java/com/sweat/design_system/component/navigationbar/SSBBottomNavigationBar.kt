package com.sweat.design_system.component.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.R
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun RowScope.SSBNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable () -> Unit,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        label = label,
        icon = if (selected) selectedIcon else icon,
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = SSBColor.main,
            unselectedIconColor = SSBColor.gray500,
            selectedTextColor = SSBColor.main,
            unselectedTextColor = SSBColor.gray500,
            indicatorColor = Color.White
        )
    )
}

@Composable
fun SSBBottomNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    SSBAndroidTheme { colors, _ ->
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .background(color = colors.gray100)
            )

            NavigationBar(
                modifier = modifier,
                containerColor = Color.White,
                contentColor = colors.gray500,
                tonalElevation = 0.dp,
                content = content
            )
        }
    }
}

@Preview
@Composable
fun SSBBottomNavigationPreview() {
    val items = listOf(
        "운동",
        "홈",
        "프로필"
    )
    val icons = listOf(
        R.drawable.dumbbell,
        R.drawable.home,
        R.drawable.profile_circle
    )
    SSBAndroidTheme { colors, typography ->
        SSBBottomNavigationBar {
            items.forEachIndexed { index, item ->
                SSBNavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item,
                            tint = colors.main
                        )
                    },
                    label = {
                        Text(
                            text = item,
                            style = typography.navi
                        )
                    },
                    selected = index == 0,
                    onClick = {},
                )
            }
        }
    }
}