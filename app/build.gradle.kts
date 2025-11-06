
plugins {
    alias(libs.plugins.meatvice.android.application)
    alias(libs.plugins.meatvice.android.application.compose)
    alias(libs.plugins.meatvice.hilt)
    alias(libs.plugins.meatvice.firebase)
}

android {
    namespace = "com.example.tasktreckervicemeat"

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }

    defaultConfig {
        applicationId = "com.example.tasktreckervicemeat"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))


    implementation(project(":feature:auth"))
    implementation(project(":feature:hubs"))
    implementation(project(":feature:profile"))



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)


    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)

    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.navigation)


    // minio
    implementation(libs.aws.sdk)

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.test.manifest)
}