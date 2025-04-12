import com.example.hospital_automation.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project){

                dependencies{

                    add("implementation",project(":core:ui-components"))
                    //lifecycle
                    add("implementation",libs.findLibrary("lifecycle.viewmodel.ktx").get())
                    add("implementation",libs.findLibrary("lifecycle.viewmodel.compose").get())
//                    //navigation
                    add("implementation",libs.findLibrary("androidx.navigation.compose").get())
//                    //koin
                    add("implementation",libs.findLibrary("koin.android").get())
                    add("implementation",libs.findLibrary("koin.androidx.compose").get())
                    add("implementation",libs.findLibrary("koin.androidx.navigation").get())

                }
            }

    }
}


/*

                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())


 */