plugins {
    id("sweat.android.feature")
    id("sweat.android.hilt")
}

android {
    namespace = "com.school_of_company.main"
}

dependencies {
    implementation("com.google.accompanist:accompanist-swiperefresh:0.27.0")
}