plugins {
    id("sweat.android.core")
    id("sweat.android.compose")
}

android {
    namespace = "com.sweat.ui"
}

dependencies {
    // todo : Add Other Project Implementation -> ex) implementation(project(":core:___")) / (project(":feature:____"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)
    implementation(libs.accompanist.permission)
}