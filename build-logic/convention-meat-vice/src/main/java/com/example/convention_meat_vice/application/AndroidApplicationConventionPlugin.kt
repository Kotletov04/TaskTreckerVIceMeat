package com.example.convention_meat_vice.application

import com.android.build.api.dsl.ApplicationExtension
import com.example.convention_meat_vice.project.configureDevicesManager
import com.example.convention_meat_vice.project.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.apply

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                // Отклбчает анимации во время тестов
                testOptions.animationsDisabled = true
                configureDevicesManager(this)
            }

            // Здесь прописать лог для тестов и апкшек


        }
    }
}