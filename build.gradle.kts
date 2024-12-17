// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.android.ksp) apply false
    alias(libs.plugins.google.maps.secrets) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.kotlinx.serialization)
}