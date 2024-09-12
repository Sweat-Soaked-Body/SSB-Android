plugins {
    id("sweat.android.core")
    id("sweat.android.hilt")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.sweat.datastore"
}

// todo : Add protobuf

dependencies {
    // todo : Add Other Project Implementation -> ex) implementation(project(":core:___")) / (project(":feature:____"))

    implementation(libs.androidx.dataStore.core)
    implementation(libs.protobuf.kotlin.lite)
}