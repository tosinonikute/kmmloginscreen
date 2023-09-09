plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.1.0-beta01").apply(false)
    id("com.android.library").version("8.1.0-beta01").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("com.google.dagger.hilt.android").version("2.48").apply(false)
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val gradleVersion = "7.4.0"
    val hiltVersion = "2.48"

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
