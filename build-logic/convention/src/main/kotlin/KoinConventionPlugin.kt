import com.example.hospital_automation.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                //koin
                add("implementation",libs.findLibrary("koin.android").get())
                add("implementation",libs.findLibrary("koin.androidx.compose").get())
                add("implementation",libs.findLibrary("koin.androidx.navigation").get())
            }
        }
    }
}