plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.hilt)
    id("org.jetbrains.kotlin.kapt")
}

apply(from = "${rootProject.projectDir}/gradle/android-library.gradle")

android {
    namespace = "akibaroom.feature.stores.di"
}

dependencies {
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    implementation(project(":features:stores"))
    implementation(project(":features:stores-api"))
    implementation(project(":core:utils"))
}
