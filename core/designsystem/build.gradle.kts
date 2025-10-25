plugins {
    alias(libs.plugins.meatvice.android.library.compose)
    alias(libs.plugins.meatvice.android.library)
}

android {
    namespace = "com.example.designsystem"
}

dependencies {

    // compose graphics
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.material3)
    api(libs.androidx.compose.navigation)

    // coil
    implementation(libs.coil3.compose)
    implementation(libs.coil3.network)

    // animation
    implementation(libs.lottie.animation)





    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}