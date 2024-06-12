plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hiltAgp)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    kotlin("kapt")
}

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    detekt {
        config = files("../detekt.yml")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.navigation.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.okHttp)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.gson)
    implementation(libs.timber)
    implementation(libs.logger)

    testImplementation(libs.composeUiTestJUnit4)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(libs.testCore)
    testImplementation(libs.hamcrest)
    testImplementation(libs.coreTesting)
    testImplementation(libs.robolectric)
    testImplementation(libs.coroutineTest)
    testImplementation(libs.junit4)

    implementation(libs.dagger)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltAndroidCompiler)
    kapt(libs.hiltCompiler)
    implementation("com.squareup:javapoet:1.13.0")
}

ktlint {
    debug = true       // set to true to see more details during the run
    verbose = true     // set to true to enable verbose logging
    android = true     // set to true if you are using Android
    outputToConsole = true  // prints the ktlint output to the console
    ignoreFailures = true  // set to true to ignore failures
    enableExperimentalRules = true  // set to true to enable experimental rules
}