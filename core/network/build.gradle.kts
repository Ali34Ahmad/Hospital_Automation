plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.kotlinx.serialization)
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.ktor.android)

    implementation(libs.koin.android)
}