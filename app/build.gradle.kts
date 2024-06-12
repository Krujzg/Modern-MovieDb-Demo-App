plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hiltAgp)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    kotlin("kapt")
    id("com.google.gms.google-services")
    alias(libs.plugins.google.firebase.appdistribution)
}

android {
    namespace = "com.example.demomoviedbapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.demomoviedbapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    detekt {
        config = files("../detekt.yml")
    }

    buildTypes {
        debug {
            firebaseAppDistribution {
                groups="QA"
            }
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            firebaseAppDistribution {
                groups="QA"
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.composeUiTestManifest)

    implementation(libs.dagger)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltAndroidCompiler)
    kapt(libs.hiltCompiler)
    implementation(project(":movies"))
    implementation(project(":core"))
    implementation("com.squareup:javapoet:1.13.0")
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
}

ktlint {
    debug = true       // set to true to see more details during the run
    verbose = true     // set to true to enable verbose logging
    android = true     // set to true if you are using Android
    outputToConsole = true  // prints the ktlint output to the console
    ignoreFailures = true  // set to true to ignore failures
    enableExperimentalRules = true  // set to true to enable experimental rules
}
