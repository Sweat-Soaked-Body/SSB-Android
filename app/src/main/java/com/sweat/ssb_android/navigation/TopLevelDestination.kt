package com.sweat.ssb_android.navigation

import com.sweat.design_system.R

enum class TopLevelDestination(
    val unselectedIcon: Int,
    val iconText: String,
    val routeName: String,
) {
    Exercise(
        unselectedIcon = R.drawable.dumbbell,
        iconText = "운동",
        routeName = "ExerciseRoute"
    ),

    Home(
        unselectedIcon = R.drawable.home,
        iconText = "홈",
        routeName = "HomeRoute"
    ),

    Profile(
        unselectedIcon = R.drawable.profile_circle,
        iconText = "프로필",
        routeName = "ProfileRoute",
    )
}