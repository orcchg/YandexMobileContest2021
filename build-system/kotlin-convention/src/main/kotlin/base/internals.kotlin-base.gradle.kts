import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.orcchg.stockshock.infra.plugins.utility.withVersionCatalogs
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

plugins {
// TODO   id("convention.detekt")
    id("convention.ktlint")
    id("convention.spotless")
    id("dependencies-alphabetical-order")
}

withVersionCatalogs {
    dependencies {
        add("implementation", kotlin.stdlib)
        add("implementation", kotlin.reflect)
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = versions.javaVersion.get()
            allWarningsAsErrors = false
            freeCompilerArgs = freeCompilerArgs +
                "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
}
