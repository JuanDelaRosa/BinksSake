plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
}

apply(from = "${rootProject.projectDir}/gradle/android-library.gradle")

android {
    namespace = "binkssake.feature.stores.di"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(project(":features:stores"))
    implementation(project(":features:stores-api"))
}
