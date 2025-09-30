plugins {
    alias(libs.plugins.common.android.application.compose)
    alias(libs.plugins.common.android.application)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.hospital_automation"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hospital_automation"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        resources {
            excludes += "META-INF/INDEX.LIST"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.bundles.koin.android)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)

    // remove network, data and datastore modules
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(project(":core:navigation"))

    //Auth features
    implementation(project(":feature:signup"))
    implementation(project(":feature:email_verification"))
    implementation(project(":feature:login"))
    implementation(project(":feature:upload_employee_documents"))
    implementation(project(":feature:add_residential_address"))
    implementation(project(":feature:enter_email"))
    implementation(project(":feature:reset_password"))
    implementation(project(":feature:upload_profile_image"))
    implementation(project(":feature:employment_history"))
    implementation(project(":feature:employee_home"))

    //
    implementation(project(":feature:employee_profile"))
    implementation(project(":feature:admin_profile"))


    //features
    implementation(project(":feature:guardians"))
    implementation(project(":feature:guardians_search"))
    implementation(project(":feature:guardian_profile"))

    implementation(project(":feature:children_search"))
    implementation(project(":feature:child_profile"))
    implementation(project(":feature:children"))
    implementation(project(":feature:add_child_screen"))
    implementation(project(":feature:upload_child_documents"))

    //data
    implementation(project(":core:data"))

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.navigation)

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(project(":core:ui-components"))

}