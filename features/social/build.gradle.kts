plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.hilt)
    id("org.jetbrains.kotlin.kapt")
}

apply(from = "${rootProject.projectDir}/gradle/android-library.gradle")

android {
    namespace = "akibaroom.feature.social"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(libs.retrofit.core)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.androidx.browser)
    implementation(libs.dagger.hilt.android)
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.dagger.hilt.compiler)
    debugImplementation(libs.ui.tooling)
    testImplementation(libs.test.junit4)
    testImplementation(libs.test.turbine)
    testImplementation(libs.test.kotest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.mockito.kotlin)

}

kapt {
    correctErrorTypes = true
}
