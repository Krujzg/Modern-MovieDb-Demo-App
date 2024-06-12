# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep the AppScope annotation
-keep @interface com.example.core.infrastructure.annotations.AppScope

# Keep the IDebugLogger interface and its implementations
-keep interface com.example.core.logging.IDebugLogger
-keep class com.example.core.logging.IDebugLogger { *; }

# Keep the SharedModule_ProvideFormatStrategyFactory class and its members
-keep class com.example.core.presentation.SharedModule_ProvideFormatStrategyFactory { *; }

# Keep the SharedModule_ProvideTimberLoggerFactory class and its members
-keep class com.example.core.presentation.SharedModule_ProvideTimberLoggerFactory { *; }
