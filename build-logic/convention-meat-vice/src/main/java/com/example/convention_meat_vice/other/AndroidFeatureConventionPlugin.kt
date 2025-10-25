package com.example.convention_meat_vice.other

import com.example.convention_meat_vice.project.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "meatvice.android.library.compose")
            apply(plugin = "meatvice.android.library")
            apply(plugin = "meatvice.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                // Находятся расширения через api() для compose, а также все кастомные элементы
                "implementation"(project(":core:designsystem"))

                // Для работы hiltViewModel, подлючается именно здесь, так как нужна только в фичах
                "implementation"(libs.findLibrary("hilt-compose-navigation").get())

                "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}