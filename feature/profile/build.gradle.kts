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
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-gif:2.4.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")

    implementation("androidx.camera:camera-core:1.3.0")
    implementation("androidx.camera:camera-camera2:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0")
    implementation("androidx.camera:camera-view:1.3.0")
    implementation("com.google.mlkit:barcode-scanning:17.2.0")
    implementation("com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.1")
}
