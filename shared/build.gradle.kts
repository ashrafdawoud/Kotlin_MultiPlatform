import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.5.10"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {

    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosFood1Fork/Podfile")
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:1.5.2")
                implementation("io.ktor:ktor-client-serialization:1.5.2")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
                implementation("com.squareup.sqldelight:runtime:1.4.3")

            }
        }
        val commonTest by getting {

            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }

        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.5.2")
                implementation("com.squareup.sqldelight:android-driver:1.4.3")
            }
        }

        val androidTest by getting {

            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                // configure kapt to utilize
                // declare the implementation for the compiler

            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.5.2")
                implementation("com.squareup.sqldelight:native-driver:1.4.3")
            }
        }
        val iosTest by getting
    }

}

android {

    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }

}
sqldelight {
    database("RecipeDatabase") {
        packageName = "com.example.food1fork.Food1ForkKmm.DataSource.Cashe"
        sourceFolders = listOf("SqlDelight")
    }
}
