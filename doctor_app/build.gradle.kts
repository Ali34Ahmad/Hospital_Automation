plugins {
    alias(libs.plugins.common.android.application.compose)
    alias(libs.plugins.common.android.application)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.doctor_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.doctor_app"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)

    //data
    implementation(project(":core:data"))
    implementation(project(":core:ui-components"))

    //features
    // remove network, data and datastore modules
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(project(":core:navigation"))

    //Auth features
    implementation(project(":feature:doctor_signup"))
    implementation(project(":feature:email_verification"))
    implementation(project(":feature:login"))
    implementation(project(":feature:upload_employee_documents"))
    implementation(project(":feature:add_residential_address"))
    implementation(project(":feature:enter_email"))
    implementation(project(":feature:reset_password"))
    implementation(project(":feature:upload_employee_profile_image"))
    implementation(project(":feature:employment_history"))
    implementation(project(":feature:prescription_details"))
    implementation(project(":feature:medical_records"))

    implementation(project(":feature:vaccine_details"))
    implementation(project(":feature:vaccines"))
    implementation(project(":feature:pharmacy_details"))
    implementation(project(":feature:admin_profile"))

    implementation(project(":feature:medical_prescriptions"))

    implementation(project(":feature:doctor_profile"))

    implementation(project(":feature:add_new_vaccine"))

    // Ali mansoura modules, delete this comment after successfully working.

    implementation(project(":feature:clinics_search"))
    implementation(project(":feature:clinic_details"))
    implementation(project(":feature:doctor-schedule"))
    implementation(project(":feature:appointment_details"))
    implementation(project(":feature:medical_diagnosis"))
    implementation(project(":feature:medicine_details"))
    implementation(project(":feature:medicines_search"))
    implementation(project(":feature:pharmacies"))
    implementation(project(":feature:guardian_profile"))

    coreLibraryDesugaring(libs.desugar.jdk.libs)



}