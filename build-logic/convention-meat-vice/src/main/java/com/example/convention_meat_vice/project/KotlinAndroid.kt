package com.example.convention_meat_vice.project

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {

    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            // Дешугаринг)))
            isCoreLibraryDesugaringEnabled = true
        }
    }

    dependencies {
        // Библеотека в libs для дешугаринга
        "coreLibraryDesugaring"(libs.findLibrary("android-desugarJdkLibs").get())
    }

    configureKotlin<KotlinAndroidProjectExtension>()
}


// Базовые настройки для non-android модулей
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    configureKotlin<KotlinJvmProjectExtension>()
}



// Базовые Kotlin настройки
private inline fun <reified T: KotlinBaseExtension> Project.configureKotlin() = configure<T> {
    // Проверяем проперти на наличие парраметра warningsAsErrors=true\false если его нет, то по-умолчанию false
    val warningsAsErrors = providers.gradleProperty("warningsAsErrors").map {
        it.toBoolean()
    }.orElse(false)
    // У всех T типов будут одинаковые базовые настройки, указанные в apply {}
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions // Сюда пожно добавлять специфичные настройки
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget = JvmTarget.JVM_11
        // Флаг, который устанавливает - предупреждение = ошибка
        allWarningsAsErrors = warningsAsErrors
        freeCompilerArgs.add(
            // Разрешаем экспереметальные функции корутин, Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }


}

