import com.android.build.gradle.BaseExtension
import com.orcchg.stockshock.infra.plugins.utility.withVersionCatalogs
import org.gradle.kotlin.dsl.configure

plugins {
    id("org.gradle.android.cache-fix")
}

withVersionCatalogs {
    configure<BaseExtension> {
        sourceSets {
            named("main").configure {
                java.srcDir("src/main/java")
                java.srcDir("src/main/kotlin")
                res.srcDir("src/test/resources")
            }
            named("debug").configure {
                java.srcDir("src/debug/java")
                java.srcDir("src/debug/kotlin")
            }
            named("release").configure {
                java.srcDir("src/release/java")
                java.srcDir("src/release/kotlin")
            }
            named("androidTest").configure {
                assets.srcDir("src/androidTest/assets")
                java.srcDir("src/androidTest/java")
                java.srcDir("src/androidTest/kotlin")
            }
            named("test").configure {
                java.srcDir("src/test/java")
                java.srcDir("src/test/kotlin")
            }
        }

        compileSdkVersion(versions.androidSdk.compileSdkVersion.get().toInt())

        compileOptions {
            sourceCompatibility = JavaVersion.toVersion(versions.javaVersion.get())
            targetCompatibility = JavaVersion.toVersion(versions.javaVersion.get())
        }

        defaultConfig {
            minSdk = versions.androidSdk.minSdkVersion.get().toInt()
            targetSdk = versions.androidSdk.targetSdkVersion.get().toInt()

            versionCode = 1
            versionName = "1.1.0"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testApplicationId = "com.orcchg.stockshock.test"
        }

        buildTypes {
            getByName("release") {
//                isTestCoverageEnabled = true // since we only have 'release' build type for libraries
            }
            getByName("debug") {
//                isTestCoverageEnabled = true
            }
        }

        lintOptions {
            isAbortOnError = false
            isCheckReleaseBuilds = false
            isWarningsAsErrors = true
            textReport = true
            isQuiet = true
        }

        testOptions {
            animationsDisabled = true
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
                all { test ->
                    test.jvmArgs("-noverify", "-Xmx7168m")
                }
            }
        }

        with(buildFeatures) {
            aidl = false
            compose = false
            buildConfig = false
            prefab = false
            renderScript = false
            resValues = false
            shaders = false
            viewBinding = false
        }
    }
}
