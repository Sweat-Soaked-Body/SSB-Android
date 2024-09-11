import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import sweat_soaked_body.configureKotlinAndroid
import sweat_soaked_body.libs

// Plugin class for configuring Android application project settings
class AndroidApplicationConventionPlugin : Plugin<Project> {

    // Entry point for the plugin
    override fun apply(target: Project) {
        with(target) {
            // Apply necessary plugins for Android and Kotlin
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                // todo : apply("*.android.lint")
            }

            // Configure Android application extension settings
            extensions.configure<ApplicationExtension> {
                // Apply common Kotlin Android settings
                configureKotlinAndroid(this)
                // Configure default settings for the application
                defaultConfig {
                    applicationId = "com.example.build"
                    minSdk = 26
                    targetSdk = 34
                    versionCode = 19
                    versionName = "1.2.8"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    vectorDrawables.useSupportLibrary = true
                }

                // Enable Jetpack Compose feature for the project
                buildFeatures.compose = true

                // Configure build types
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                // Set Compose compiler extension version from the Version Catalog
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
                }

                // Add necessary dependencies using Version Catalog bundles
                dependencies {
                    add("implementation",libs.findBundle("kotlinx-coroutines").get())
                    add("implementation",libs.findBundle("compose").get())
                }
            }
        }
    }
}