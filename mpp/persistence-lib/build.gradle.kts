plugins {
    kotlin("multiplatform") version "1.3.72"
}

kotlin {
    jvm()
    jvm("android")
    js()

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
    }

    sourceSets["jvmMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    sourceSets["jsMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}