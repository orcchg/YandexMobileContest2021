import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("convention.libraries")
}

dependencies {
    //implementation fileTree(dir: 'libs', include: ['*.jar'])
    add("implementation", libs.kotlinReflect)
    add("implementation", libs.kotlinStdLib)
//    implementation(libs.kotlinStdlib) // TODO: figure out issue with `kotlin-dsl`
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = libs.javaVersion.toString()
        allWarningsAsErrors = true
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}