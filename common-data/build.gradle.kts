plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.mekami.common_data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common-domain"))
    implementation(libs.ktor.client.android)

    // Coroutines
    implementation(libs.coroutines.core)

    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization.json)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}