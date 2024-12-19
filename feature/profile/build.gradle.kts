plugins {
    id("sweat.android.feature")
    id("sweat.android.hilt")
}

android {
    namespace = "com.sweat.profile"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
}
