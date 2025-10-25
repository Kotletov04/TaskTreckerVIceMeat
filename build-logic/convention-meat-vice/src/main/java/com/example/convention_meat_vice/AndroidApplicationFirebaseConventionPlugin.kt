package com.example.convention_meat_vice

import com.example.convention_meat_vice.project.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationFirebaseConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.gms.google-services")

            dependencies {
                "implementation"(platform(libs.findLibrary("firebase-bom").get()))
                "implementation"(libs.findLibrary("firebase-analitycs").get())
                "implementation"(libs.findLibrary("firebase-auth").get())
                "implementation"(libs.findLibrary("firebase-auth-play-services").get())
                "implementation"(libs.findLibrary("firebase-firestore").get())
                "implementation"(libs.findLibrary("firebase-base-play-services").get())

            }

        }

    }
}