plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mekami.pichu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mekami.pichu"
        minSdk = 24
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
}

dependencies {
    api(project(":common"))
    api(project(":games"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.live.data)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.foundation)
    implementation(libs.androidx.compose.ui.material3)
    implementation(libs.androidx.compose.ui.material3.window.size)

    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.lifecycle)

    implementation(libs.dagger)
    implementation(libs.dagger.navigation)
    implementation(libs.dagger.navigation.compose)
    kapt(libs.dagger.compiler)
}