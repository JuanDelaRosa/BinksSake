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
    namespace = "akibaroom.core.firebase"
}

dependencies {
    // Firebase BOM
    implementation(platform(libs.firebase.bom))
    
    // Firebase Auth
    implementation(libs.firebase.auth)
    
    // Firebase Realtime Database
    implementation(libs.firebase.database)
    
    // FirebaseUI Auth
    implementation(libs.firebaseui.auth)
    
    // Coroutines for Firebase
    implementation(libs.play.services.coroutines)
    
    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    
    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
}
