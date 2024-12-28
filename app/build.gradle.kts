plugins {
    id("sweat.android.application")
    id("sweat.android.hilt")
}

android {
    namespace = "com.sweat.ssb_android"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
        }
    }
}

dependencies {
    // todo : Add Other Project Implementation -> ex) implementation(project(":core:___")) / (project(":feature:____"))
    implementation(project(":core:design-system"))
    implementation(project(":core:ui"))
    implementation(project(":feature:signup"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:login"))
    implementation(project(":feature:main"))
    implementation(project(":feature:exercise"))
    implementation(project(":feature:main"))
    implementation(project(":core:common"))
    implementation(project(":feature:main"))

    implementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    implementation(libs.app.update.ktx)
}