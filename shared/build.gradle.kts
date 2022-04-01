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
    iosX64()//
    iosArm64()//
    //iosSimulatorArm64() //sure all ios dependencies support this target

    /* val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
         System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
         else -> ::iosX64
     }

     iosTarget("ios") {}*/

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
               // implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
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
        val iosX64Main by getting
        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.5.2")
                implementation("com.squareup.sqldelight:native-driver:1.4.3")
            }
           // iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
       // val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
           // iosSimulatorArm64Test.dependsOn(this)

        }
      /*  val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.5.2")
                implementation("com.squareup.sqldelight:native-driver:1.4.3")
            }
        }
        val iosTest by getting*/
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
