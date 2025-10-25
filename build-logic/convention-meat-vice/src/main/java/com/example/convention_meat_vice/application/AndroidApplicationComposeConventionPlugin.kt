package com.example.convention_meat_vice.application

import com.android.build.api.dsl.ApplicationExtension
import com.example.convention_meat_vice.project.configureAndroidCompose
import com.example.convention_meat_vice.project.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(commonExtension = extension)

            dependencies {
                // Для работы hiltViewModel, подлючается именно здесь, так как нужна  НЕ только в фичах
                "implementation"(libs.findLibrary("hilt-compose-navigation").get())
            }
        }
    }
}