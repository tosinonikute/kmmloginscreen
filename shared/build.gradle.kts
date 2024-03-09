plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {

    android()
    jvm()

    val coroutinesVersion = "1.6.4"
    val junitVersion = "4.13.2"
    val mockitoVersion = "4.0.0"
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(mapOf("path" to ":shared:textvalidator")))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("junit:junit:$junitVersion")
                implementation("org.mockito:mockito-inline:$mockitoVersion")
            }
        }
    }
}

android {
    namespace = "com.kmmloginscreen"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}

dependencies {
    implementation(project(mapOf("path" to ":shared:textvalidator")))
}
