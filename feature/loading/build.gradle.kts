plugins {
    alias(libs.plugins.meatvice.android.library.compose)
    alias(libs.plugins.meatvice.android.library)
    alias(libs.plugins.meatvice.android.feature)
}

android {
    namespace = "com.example.profile"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}