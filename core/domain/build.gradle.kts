plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.juinit.api)
    testImplementation(libs.juinit.platform.suite)
    testImplementation(libs.juinit.engine)
    testImplementation(libs.juinit.params)
}
