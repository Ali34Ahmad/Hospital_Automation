import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.library.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.ui_components"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    defaultConfig {
        minSdk = 27
    }
    compileSdk = 35

}


dependencies {

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.composepininput)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.ui.graphics.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.core)
    implementation(libs.calendar)
    implementation(libs.duration)
    implementation(libs.option)

    implementation(project(":core:model"))

    implementation(project(":core:model"))
//    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.androidx.foundation.layout)
}
