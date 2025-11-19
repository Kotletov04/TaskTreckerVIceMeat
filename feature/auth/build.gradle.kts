plugins {
    alias(libs.plugins.meatvice.android.library.compose)
    alias(libs.plugins.meatvice.android.library)
    alias(libs.plugins.meatvice.android.feature)
}

android {
    namespace = "com.example.auth"
}
dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":feature:feature-common"))

}
