import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hiltAgp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    kotlin("kapt")
}

android {
    namespace = "com.example.movies"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "com.example.movies.HiltTestRunner"
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

    buildFeatures.buildConfig = true

    val localProperties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

    val baseUrl = localProperties.getProperty("base_url")
    val apiKey = localProperties.getProperty("api_key")

    if (baseUrl != null && apiKey != null) {
        buildTypes {
            forEach { buildType ->
                named(buildType.name) {
                    buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
                    buildConfigField("String", "API_KEY", "\"$apiKey\"")
                }
            }
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
    testOptions.unitTests.isIncludeAndroidResources = true

    detekt {
        config = files("../detekt.yml")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.dagger)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltAndroidCompiler)
    kapt(libs.hiltCompiler)
    implementation(libs.okHttp)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.gson)
    implementation(libs.dataStore)
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    implementation(libs.material)
    implementation(libs.materialIcons)
    implementation(libs.timber)
    implementation(libs.logger)
    implementation(libs.leakCanary)
    implementation(libs.coil)

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
    debugImplementation(libs.composeUiTestManifest)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.testExtJUnit)
    androidTestImplementation(libs.espressoCore)
    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48.1")
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.48.1")


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(project(":core"))
    implementation("com.squareup:javapoet:1.13.0")
}