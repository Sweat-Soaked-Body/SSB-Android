plugins {
    id("sweat.android.core")
    id("sweat.android.hilt")
}

android {
    // todo : buildConfig

    namespace = "com.sweat.network"
}

dependencies {
    // todo : Add Other Project Implementation -> ex) implementation(project(":core:___")) / (project(":feature:____"))
    implementation(project(":core:common"))

    debugImplementation(libs.debug.chuck)
    releaseImplementation(libs.release.chuck)

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.moshi)
    ksp(libs.retrofit.moshi.codegen)
}

// todo : Create getApiKey