plugins {
    alias(libs.plugins.meatvice.android.library)
    alias(libs.plugins.meatvice.firebase)
    alias(libs.plugins.meatvice.hilt)
    alias(libs.plugins.room)
}

android {
    namespace = "com.example.data"
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    // module
    implementation(project(":core:domain"))

    // room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit.gson.converter)

    // minio sdk
    implementation(libs.aws.sdk)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}