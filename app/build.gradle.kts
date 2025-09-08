plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.quetzapps.akibaroom"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.quetzapps.akibaroom"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:database"))
    implementation(project(":core:ui"))
    implementation(project(":core:utils"))

    implementation(project(":features:auth-api"))
    implementation(project(":features:auth"))
    implementation(project(":features:collection-api"))
    implementation(project(":features:collection"))
    implementation(project(":features:figures-api"))
    implementation(project(":features:figures"))
    implementation(project(":features:profile-api"))
    implementation(project(":features:profile"))
    implementation(project(":features:social-api"))
    implementation(project(":features:social"))
    implementation(project(":features:store-api"))
    implementation(project(":features:store"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)
    implementation(libs.android.material)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.firestore)
    implementation(libs.play.services.coroutines)
    testImplementation(libs.test.junit4)
    androidTestImplementation(libs.test.androidx.junit)
    androidTestImplementation(libs.test.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.androidx.paging.runtime)
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("dagger.hilt.internal.useAggregatingRootProcessor", "true")
    }
}
