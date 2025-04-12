plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.network"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.ktor.android)

    implementation(libs.koin.android)

}