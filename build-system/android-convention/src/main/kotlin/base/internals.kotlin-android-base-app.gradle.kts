plugins {
    id("com.android.application")
    id("kotlin-android")
    id("internals.kotlin-base")
    id("internals.android-base")
    id("convention.jacoco-android")
    id("convention.sonarqube-android")
}

android {
    testBuildType = "debug"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName(testBuildType) {
            // libraries only built in release variant, see convention.kotlin-android-library
            matchingFallbacks += listOf("release")
        }
    }

    packagingOptions {
        resources.excludes.add("META-INF/*.kotlin_module")
    }
}
