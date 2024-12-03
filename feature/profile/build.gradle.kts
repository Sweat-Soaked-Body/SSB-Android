plugins {
    id("sweat.android.feature")
    id("sweat.android.hilt")
}

android {
    namespace = "com.meister.profile"
}
dependencies {
    implementation(project(":core:common"))
}
