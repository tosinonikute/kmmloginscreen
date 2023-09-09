plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    val junitVersion = "4.13.2"
    val mockitoVersion = "4.0.0"
    sourceSets {
        dependencies {
            implementation("junit:junit:$junitVersion")
            implementation("org.mockito:mockito-inline:$mockitoVersion")
        }
    }
}
