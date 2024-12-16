plugins {
    id("sweat.android.feature")
    id("sweat.android.hilt")
}

android {
    namespace = "com.sweat.login"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
}
