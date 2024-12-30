plugins {
    id("sweat.android.feature")
    id("sweat.android.hilt")
}

android {
    namespace = "com.school_of_company.food"
}

dependencies {
    implementation(project(":feature:exercise"))
}