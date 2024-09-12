import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "sweat.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidHilt") {
            id = "sweat.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLint") {
            id = "sweat.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }

        register("androidCore") {
            id = "sweat.android.core"
            implementationClass = "AndroidCoreConventionPlugin"
        }

        register("androidCompose") {
            id = "sweat.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("jvmLibrary") {
            id = "sweat.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "sweat.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}