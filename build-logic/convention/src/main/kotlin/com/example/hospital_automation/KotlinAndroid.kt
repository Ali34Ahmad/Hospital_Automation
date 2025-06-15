package com.example.hospital_automation


import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import org.jetbrains.kotlin.gradle.plugin.KOTLIN_DSL_NAME
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*,*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 27
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        configureKotlin()
    }
}
private fun Project.configureKotlin() {
    plugins.withType(KotlinPluginWrapper::class.java) {
        project.extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>(KOTLIN_DSL_NAME) {
            jvmToolchain(11)
        }
    }
}