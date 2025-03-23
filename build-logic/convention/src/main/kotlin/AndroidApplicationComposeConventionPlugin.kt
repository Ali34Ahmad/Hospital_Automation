import com.android.build.api.dsl.ApplicationExtension
import com.example.hospital_automation.configureAndroidCompose
import com.example.hospital_automation.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            project.pluginManager.apply("com.android.application")
            project.pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)

                dependencies {
                    add("implementation", libs.findLibrary("androidx.activity.compose").get())
                }
            }
        }
    }
}