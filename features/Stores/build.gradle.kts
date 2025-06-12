plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
}

apply(from = "${rootProject.projectDir}/gradle/android-library.gradle")

android {
    namespace = "binkssake.feature.stores"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":features:stores-api"))
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose.android)
}
