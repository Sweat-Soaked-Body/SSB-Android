package com.sweat.ssb_android.navigation

enum class TopLevelDestination(
    // val unselectedIcon: Int,
    val iconText: String
) {
    Exercise(
        // unselectedIcon = R.drawable.ic_exercise_24,
        iconText = "운동"
    ),

    Home(
        // unselectedIcon = R.drawable.ic_home_24,
        iconText = "홈"
    ),

    Profile(
        // unselectedIcon = R.drawable.ic_profile_24,
        iconText = "프로필"
    )
}