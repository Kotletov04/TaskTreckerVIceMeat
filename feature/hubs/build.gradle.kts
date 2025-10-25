plugins {
    alias(libs.plugins.meatvice.android.library.compose)
    alias(libs.plugins.meatvice.android.library)
    alias(libs.plugins.meatvice.android.feature)
}

android {
    namespace = "com.example.hubs"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}