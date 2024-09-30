plugins {
    id("sweat.android.core")
    id("sweat.android.hilt")
}

android {
    namespace = "com.sweat.common"
}

dependencies {
    // Add Implementation
    implementation(libs.androidx.lifecycle.viewModel.ktx)
}