plugins {
    `kotlin-dsl`
}

group = "com.meatvice.meatline.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    /*

    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)

    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)*/

}

// Строгая проверка кастомных плагинов на потенциальные проблемы
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.meatvice.android.application.compose.get().pluginId
            implementationClass = "com.example.convention_meat_vice.application.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.meatvice.android.application.asProvider().get().pluginId
            implementationClass = "com.example.convention_meat_vice.application.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.meatvice.android.library.compose.get().pluginId
            implementationClass = "com.example.convention_meat_vice.library.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.meatvice.android.library.asProvider().get().pluginId
            implementationClass = "com.example.convention_meat_vice.library.AndroidLibraryConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.meatvice.hilt.get().pluginId
            implementationClass = "com.example.convention_meat_vice.other.HiltConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.meatvice.android.feature.get().pluginId
            implementationClass = "com.example.convention_meat_vice.other.AndroidFeatureConventionPlugin"
        }
        register("firebase") {
            id = libs.plugins.meatvice.firebase.get().pluginId
            implementationClass = "com.example.convention_meat_vice.AndroidApplicationFirebaseConventionPlugin"
        }
    }
}