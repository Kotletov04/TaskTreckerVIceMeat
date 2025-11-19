package com.example.convention_meat_vice.project

import com.android.build.api.dsl.CommonExtension
import org.gradle.kotlin.dsl.*
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

internal fun Project.configureJUnit(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {

        tasks.withType<Test> {
            useJUnitPlatform()
        }
        dependencies {
            "testImplementation"(libs.findLibrary("juinit-api").get())
            "testImplementation"(libs.findLibrary("juinit-engine").get())
            "testImplementation"(libs.findLibrary("juinit-params").get())
            "testImplementation"(libs.findLibrary("juinit-platform-suite").get())
        }

    }


}