import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlinVersion
    jacoco
    application

}

allprojects {
    group = ApplicationSettings.groupName
    version = ApplicationSettings.version

    repositories {
        jcenter()
        mavenCentral()
        // https://kotlin.bintray.com = https://dl.bintray.com/kotlin
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    }
}

allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.kotlinCompilerJvmTargetVersion
        }
    }

    tasks.withType<Jar> {
        manifest {
            attributes["Implementation-Vendor"] = ApplicationSettings.groupName
            attributes["Implementation-Title"] = "${ApplicationSettings.name}.${project.name}"
            attributes["Implementation-Version"] = ApplicationSettings.version
        }
    }
}

dependencies {
    implementation(Dependencies.org_jetbrains_kotlinx_coroutines_core)
    implementation(Dependencies.org_jetbrains_kotlinx_serialization_json)
    implementation(Dependencies.org_jetbrains_kotlinx_datetime)
    implementation(Dependencies.ch_qos_logback_classic)

    testImplementation(Dependencies.org_jetbrains_kotlin_test)
    testImplementation(Dependencies.org_jetbrains_kotlin_test_junit5)
    testImplementation(Dependencies.org_junit_jupiter_engine)
}

val applicationMainClassName = "${project.name}.App"

application {
    mainClassName = applicationMainClassName
}

val runAppTask = tasks.create<JavaExec>("runApp") {
    // https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html
    classpath = sourceSets.main.get().runtimeClasspath
    main = applicationMainClassName
    println("Task $name from project ${project.name} with executable=$executable, workingDir=${workingDir.absolutePath}, executable=${executable}, javaVersion=$javaVersion path=$path")

    val workingDirectoryAbsolutePath = workingDir.absolutePath
    args = arrayOf("-Dlogback.configurationFile=$workingDirectoryAbsolutePath\\build\\resources\\main\\logback.xml").toList()
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    doLast {
        println("View code coverage at:")
        println("file://$buildDir\\jacocoHtml\\index.html")
    }
    testLogging {
        showStandardStreams = true
        showExceptions = true
        showCauses = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.isEnabled = true
        html.destination = file("$buildDir/jacocoHtml")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.95".toBigDecimal()
            }
        }
    }
}
