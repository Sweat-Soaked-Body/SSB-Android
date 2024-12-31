package com.sweat.ssb_android.navigation

import com.school_of_company.main.navigation.homeRoute
import com.sweat.design_system.R
import com.sweat.exercise.exerciseRoute
import com.sweat.profile.profileRoute

enum class TopLevelDestination(
    val unselectedIcon: Int,
    val iconText: String,
    val routeName: String,
) {
    Exercise(
        unselectedIcon = R.drawable.dumbbell,
        iconText = "운동",
        routeName = exerciseRoute
    ),

    Home(
        unselectedIcon = R.drawable.home,
        iconText = "홈",
        routeName = homeRoute
    ),

    Profile(
        unselectedIcon = R.drawable.profile_circle,
        iconText = "프로필",
        routeName = profileRoute,
    )
}