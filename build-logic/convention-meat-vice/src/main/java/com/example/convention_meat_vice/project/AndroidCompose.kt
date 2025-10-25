package com.example.convention_meat_vice.project

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.api.provider.Provider


internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
            "implementation"(libs.findLibrary("androidx-ui-tooling-preview").get())
            "androidTestImplementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
            "debugImplementation"(libs.findLibrary("androidx-ui-tooling").get())
        }
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {

        // Функция расширяющая Provider<String>, которая возвращает либо true либо null
        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }

        // Создает внутри build директорию с отчетами по работе Compose
        fun Provider<*>.relativeToRootProject(dir: String) = map {
            isolated.rootProject.projectDirectory.dir("build").dir(projectDir.toRelativeString(rootDir))
        }.map { it.dir(dir) }

        // Если в корневом gradle.properties указано enableComposeCompilerMetrics = true, то создает метрики-отчеты
        project.providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
            .relativeToRootProject("compose-metrics")
            .let(metricsDestination::set)

        // Если в корневом gradle.properties указано enableComposeCompilerReports = true, то создает репорты
        project.providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()
            .relativeToRootProject("compose-reports")
            .let(reportsDestination::set)

        /*stabilityConfigurationFiles
            .add(isolated.rootProject.projectDirectory.file("compose_compiler_config.conf"))*/

    }


}

