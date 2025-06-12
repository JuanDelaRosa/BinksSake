plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
}

apply(from = "${rootProject.projectDir}/gradle/android-library.gradle")

android {
    namespace = "binkssake.core.utils"
}

dependencies {
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
}
