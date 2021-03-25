package com.orcchg.yandexcontest.build

object Versions {
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
    const val compileSdkVersion = 30

    const val appCompat = "1.2.0"
    const val autoDispose = "1.4.0"
    const val buildToolsVersion = "30.0.0"
    const val constraintLayout = "2.0.0-rc1"
    const val coreKtx = "1.3.1"
    const val dagger = "2.29"
    const val espresso = "3.2.0"
    const val finnhub = "1.2.0"
    const val fragmentKtx = "1.2.5"
    const val glide = "4.11.0"
    const val gradleAndroid = "4.1.3"
    const val hamcrest = "1.3"
    const val junit = "4.13"
    const val junitAndroidExt = "1.1.1"
    const val ktlint = "0.38.0"
    const val ktlintGradle = "9.4.1"
    const val ktStdLib = "1.4.31"
    const val lifecycle = "2.2.0"
    const val lint = "27.0.1"
    const val material = "1.3.0"
    const val moshi = "1.9.3"
    const val navigation = "2.3.4"
    const val okHttp = "4.8.1"
    const val recyclerView = "1.1.0"
    const val retrofit = "2.9.0"
    const val room = "2.2.6"
    const val rx = "2.2.19"
    const val rxAndroid = "2.1.1"
    const val rxBinding = "3.1.0"
    const val rxKt = "2.4.0"
    const val scarlet = "0.1.11"
    const val shimmer = "0.5.0"
    const val swipeRefreshLayout = "1.1.0"
    const val viewPager2 = "1.0.0"
    const val timber = "4.7.1"
}

object Dependencies {

    object AndroidX {
        @JvmStatic val appCompat = dependency("androidx.appcompat:appcompat:${Versions.appCompat}")
        @JvmStatic val coreKtx = dependency("androidx.core:core-ktx:${Versions.coreKtx}")
        @JvmStatic val fragmentKtx = dependency("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")
        @JvmStatic val liveData = dependency("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
        @JvmStatic val material = dependency("com.google.android.material:material:${Versions.material}")
        @JvmStatic val navigation = dependency("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
        @JvmStatic val navigationSafeArgs = dependency("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
        @JvmStatic val navigationUi = dependency("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
        @JvmStatic val room = dependency("androidx.room:room-runtime:${Versions.room}")
        @JvmStatic val roomCompiler = dependency("androidx.room:room-compiler:${Versions.room}")
        @JvmStatic val roomRx = dependency("androidx.room:room-rxjava2:${Versions.room}")
    }

    object Di {
        @JvmStatic val dagger = dependency("com.google.dagger:dagger:${Versions.dagger}")
        @JvmStatic val daggerCompiler = dependency("com.google.dagger:dagger-compiler:${Versions.dagger}")
    }

    object Gradle {
        @JvmStatic val androidGradle = dependency("com.android.tools.build:gradle:${Versions.gradleAndroid}")
        @JvmStatic val ktGradle = dependency("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.ktStdLib}")
    }

    object Lib {
        @JvmStatic val glide = dependency("com.github.bumptech.glide:glide:${Versions.glide}")
        @JvmStatic val ktStdLib = dependency("org.jetbrains.kotlin:kotlin-stdlib:${Versions.ktStdLib}")
        @JvmStatic val timber = dependency("com.jakewharton.timber:timber:${Versions.timber}")
    }

    object Network {
        @JvmStatic val finnhub = dependency("com.finnhub:kotlin-client:${Versions.finnhub}")
        @JvmStatic val okHttpLog = dependency("com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}")
        @JvmStatic val retrofit = dependency("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
        @JvmStatic val retrofitMoshi = dependency("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}")
        @JvmStatic val retrofitRx = dependency("com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}")
        @JvmStatic val scarlet = dependency("com.tinder.scarlet:scarlet:${Versions.scarlet}")
        @JvmStatic val scarletMoshi = dependency("com.tinder.scarlet:message-adapter-moshi:${Versions.scarlet}")
        @JvmStatic val scarletOkHttp = dependency("com.tinder.scarlet:websocket-okhttp:${Versions.scarlet}")
        @JvmStatic val scarletRx = dependency("com.tinder.scarlet:stream-adapter-rxjava2:${Versions.scarlet}")
    }

    object Parser {
        @JvmStatic val moshi = dependency("com.squareup.moshi:moshi-adapters:${Versions.moshi}")
        @JvmStatic val moshiKotlin = dependency("com.squareup.moshi:moshi-kotlin:${Versions.moshi}")
        @JvmStatic val moshiKotlinCodegen = dependency("com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}")
    }

    object Plugin {
        const val androidApp = "com.android.application"
        const val androidDynamicFeature = "com.android.dynamic-feature"
        const val androidLib = "com.android.library"
        const val javaLib = "java-library"
        const val google = "com.google.gms.google-services"
        const val kotlin = "kotlin"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinAnnotation = "kotlin-kapt"
        const val ktlint = "org.jlleitschuh.gradle.ktlint"
        const val navSafeArgs = "androidx.navigation.safeargs.kotlin"
    }

    object Rx {
        @JvmStatic val autoDispose = dependency("com.uber.autodispose:autodispose:${Versions.autoDispose}")
        @JvmStatic val autoDisposeAndroidAC = dependency("com.uber.autodispose:autodispose-android-archcomponents:${Versions.autoDispose}")
        @JvmStatic val rx = dependency("io.reactivex.rxjava2:rxjava:${Versions.rx}")
        @JvmStatic val rxKt = dependency("io.reactivex.rxjava2:rxkotlin:${Versions.rxKt}")
        @JvmStatic val rxAndroid = dependency("io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}")
        @JvmStatic val rxBinding = dependency("com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}")
        @JvmStatic val rxBindingCore = dependency("com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}")
        @JvmStatic val rxBindingSwipe = dependency("com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.rxBinding}")
    }

    object View {
        @JvmStatic val constraintLayout = dependency("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")
        @JvmStatic val recyclerView = dependency("androidx.recyclerview:recyclerview:${Versions.recyclerView}")
        @JvmStatic val shimmer = dependency("com.facebook.shimmer:shimmer:${Versions.shimmer}")
        @JvmStatic val swipeRefreshLayout = dependency("androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}")
        @JvmStatic val viewPager2 = dependency("androidx.viewpager2:viewpager2:${Versions.viewPager2}")
    }

    object Lint {
        @JvmStatic val lintApi = dependency("com.android.tools.lint:lint-api:${Versions.lint}")
        @JvmStatic val lintChecks = dependency("com.android.tools.lint:lint-checks:${Versions.lint}")
        @JvmStatic val lintCore = dependency("com.android.tools.lint:lint:${Versions.lint}")
        @JvmStatic val lintTests = dependency("com.android.tools.lint:lint-tests:${Versions.lint}")
    }

    object AndroidTest {
        @JvmStatic val espresso = dependency("androidx.test.espresso:espresso-core:${Versions.espresso}")
        @JvmStatic val junit = dependency("androidx.test.ext:junit:${Versions.junitAndroidExt}")
    }

    object Test {
        @JvmStatic val hamcrest = dependency("org.hamcrest:hamcrest-all:${Versions.hamcrest}")
        @JvmStatic val junit = dependency("junit:junit:${Versions.junit}")
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val allProjectDependencies: MutableSet<String> = mutableSetOf()

    private fun dependency(dependency: String): String {
        allProjectDependencies.add(dependency)
        return dependency
    }
}
