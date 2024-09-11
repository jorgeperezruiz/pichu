// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://repo.repsy.io/mvn/chrynan/public") }
        maven { url = uri("https://artifacts.bitmovin.com/artifactory/public-releases") }
    }
    dependencies {
//        classpath(libs.dagger)
//        classpath(libs.dagger.plugin)
    }
}