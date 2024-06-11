import org.gradle.api.Plugin
import org.gradle.api.Project

class MainGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("java-library")
            plugin("org.jetbrains.kotlin.jvm")
            plugin("kotlin-kapt")
        }
    }
}