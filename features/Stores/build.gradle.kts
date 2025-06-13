plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
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
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose.android)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.androidx.browser)
    debugImplementation(libs.ui.tooling)
    testImplementation(libs.junit)
    testImplementation(libs.test.turbine)
    testImplementation(libs.test.kotest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.mockito.kotlin)

}
