import com.orcchg.stockshock.infra.plugins.utility.withVersionCatalogs
import com.orcchg.stockshock.staticanalysis.detekt.findDetektBaselineAll
import gradle.kotlin.dsl.accessors._caaef686956ef05d8c7d73205bf1c4b7.detektPlugins
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("io.gitlab.arturbosch.detekt")
}

configure<DetektExtension> {
    parallel = true
    ignoreFailures = false

    if (!project.displayName.contains("generatePrecompiledScriptPluginAccessors")) {
        try {
            val buildSystemProjectDir = gradle.includedBuild("build-system").projectDir.path
            val configFilePath = "$buildSystemProjectDir/static-analysis/detekt/config/detekt.yml"
            if (file(configFilePath).exists()) {
                config = files(configFilePath)
            } else {
                throw GradleException("Detekt config not found in path: '$configFilePath'")
            }
        } catch (e: UnknownDomainObjectException) {
            throw GradleException("Include build with name 'build-system' not found in project: '${project.displayName}'.")
        }
    }
    if (project.name != "gradle-kotlin-dsl-accessors" &&
        !project.name.contains("generatePrecompiledScriptPluginAccessors")
    ) {
        baseline = findDetektBaselineAll(project)
    }

    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
    }
}

dependencies {
    withVersionCatalogs {
        detektPlugins("com.orcchg.stockshock.infra:detekt-rules")
        detektPlugins(debugging.detekt.formatting)
    }
}

tasks.withType<Detekt>().configureEach {
    withVersionCatalogs {
        jvmTarget = versions.javaVersion.get()
        val detektRulesAssembleTask = gradle.includedBuild("build-system").task(":static-analysis:detekt-rules:assemble")
        dependsOn(detektRulesAssembleTask)
    }
}
