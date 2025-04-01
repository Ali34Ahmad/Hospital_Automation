import com.android.build.api.dsl.ApplicationExtension
import com.example.hospital_automation.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.plugin.KOTLIN_DSL_NAME
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(project.pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefaultConfig()
            }
        }
    }
}

private fun ApplicationExtension.configureDefaultConfig() {
    defaultConfig {
        applicationId = "com.example.hospital_automation"
        targetSdk = 34
        versionCode = 1
        minSdk = 27
        versionName = "1.0"
    }
}