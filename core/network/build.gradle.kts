plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

apply(from = "${rootProject.projectDir}/gradle/android-library.gradle")

android {
    namespace = "akibaroom.core.network"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
