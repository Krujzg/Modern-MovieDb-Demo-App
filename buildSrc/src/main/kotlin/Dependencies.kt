import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.kotlin}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAnnotationProcessor = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltAndroidX}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAgentJvm = "io.mockk:mockk-agent-jvm:${Versions.mockk}"

    const val truth = "com.google.truth:truth:${Versions.truth}"

    const val testCore = "androidx.test:core:${Versions.testCore}"
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testExtJUnit = "androidx.test.ext:junit:${Versions.testExtJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUiTestJUnit4 =
        "androidx.compose.ui:ui-test-junit4-android:${Versions.composeUIJunit}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    const val material3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val materialIcons =
        "androidx.compose.material:material-icons-extended:${Versions.materialIcons}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val logger = "com.orhanobut:logger:${Versions.logger}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leanCanary}"
}

fun DependencyHandler.materialIcon() {
    implementation(Dependencies.material3)
    implementation(Dependencies.materialIcons)
    implementation(Dependencies.material)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    debugImplementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeNavigation)
}

fun DependencyHandler.composeTesting() {
    androidTestImplementation(Dependencies.composeBom)
    androidTestImplementation(Dependencies.composeUiTestJUnit4)
    testImplementation(Dependencies.composeBom)
    testImplementation(Dependencies.composeUiTestJUnit4)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
}

fun DependencyHandler.androidTest() {
    androidTestImplementation(Dependencies.junit4)
    androidTestImplementation(Dependencies.coroutineTest)
    androidTestImplementation(Dependencies.coreTesting)
    androidTestImplementation(Dependencies.testExtJUnit)
    androidTestImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.robolectric)
    androidTestImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.testExtJUnit)
    composeTesting()
}

fun DependencyHandler.testing() {
    implementation(Dependencies.testCore)
    testImplementation(Dependencies.hamcrest)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.coroutineTest)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.mockkAgentJvm)
    testImplementation(Dependencies.coroutinesCore)
    testImplementation(Dependencies.junit4)
}

fun DependencyHandler.network() {
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gson)
    implementation(Dependencies.gsonConverter)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.coroutinesCore)
}

fun DependencyHandler.coreKtx() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
}

fun DependencyHandler.dataStore() {
    implementation(Dependencies.dataStore)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.dagger() {
    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerAnnotationProcessor)
}

fun DependencyHandler.debugLogger() {
    implementation(Dependencies.logger)
    implementation(Dependencies.timber)
}

fun DependencyHandler.crossCuttingConcers() {
    debugLogger()
    testing()
    hilt()
    coroutines()
}

fun DependencyHandler.androidSpecific() {
    debugImplementation(Dependencies.leakCanary)
}

fun DependencyHandler.application() {
    implementation(project(":application"))
}

fun DependencyHandler.domain() {
    implementation(project(":domain"))
}

fun DependencyHandler.infrastructure() {
    implementation(project(":infrastructure"))
}

fun DependencyHandler.persistance() {
    implementation(project(":persistance"))
}

fun DependencyHandler.shared() {
    implementation(project(":shared"))
}