plugins {
//    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.koin)
}

android {
    namespace = "com.example.data"


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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    api(project(":core:network"))
    api(project(":core:datastore"))
    api(project(":core:domain"))
    api(project(":core:model"))

    implementation(libs.androidx.paging.runtime)

}