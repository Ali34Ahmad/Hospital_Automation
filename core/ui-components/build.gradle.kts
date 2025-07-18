plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.library.compose)
    alias(libs.plugins.kotlin.android)
}


android {
    namespace = "com.example.ui_components"
    compileSdk = 35

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

}
dependencies {

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.ui.graphics.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.ui.tooling)
    api(libs.core)
    api(libs.calendar)
    api(libs.duration)
    api(libs.option)


    implementation(project(":core:model"))
    api(libs.androidx.material)
    api(libs.androidx.foundation.layout)
    debugImplementation(libs.ui.tooling)
}
