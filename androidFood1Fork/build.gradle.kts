plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.10"

}

dependencies {
    val composeVersion = "1.0.0-beta05"
    implementation(project(":shared"))

    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.compose.ui:ui:1.0.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.1")
    // Material Design
    implementation("androidx.compose.material:material:1.0.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.0.1")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.runtime:runtime-rxjava2:$composeVersion")
    val composeActivitiesVersion = "1.3.0-rc01"
    implementation("androidx.activity:activity-compose:${composeActivitiesVersion}")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation("com.google.dagger:hilt-android:2.37")
    implementation("androidx.hilt:hilt-navigation:1.0.0-alpha03")
    configurations.getByName("kapt").dependencies.add(
        implementation("com.google.dagger:hilt-compiler:2.37")
    )
    implementation("io.ktor:ktor-client-android:1.5.2")

}
kapt {
    correctErrorTypes = true
}
android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.example.food1fork.android"
        minSdkVersion(24)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
        composeOptions.kotlinCompilerExtensionVersion = "1.0.1"//"1.0.0-alpha08"
        composeOptions.kotlinCompilerVersion = "1.4.20" //"1.4.20"
    }
    defaultConfig {
        multiDexEnabled = true
    }
    packagingOptions {
        packagingOptions {
            resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
}