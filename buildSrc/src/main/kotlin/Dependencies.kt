object Versions {
    // https://kotlinlang.org/docs/releases.html
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin
    const val kotlinVersion = "1.5.21"
    // https://github.com/Kotlin/kotlinx.coroutines/releases
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx
    const val kotlinxCoroutinesVersion = "1.5.0"
    // https://github.com/Kotlin/kotlinx.serialization/releases
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
    const val kotlinxSerializationVersion = "1.2.1"
    // https://github.com/Kotlin/kotlinx-datetime/releases
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-datetime
    const val kotlinxDatetimeVersion = "0.2.1"
    // https://mvnrepository.com/artifact/ch.qos.logback
    const val logbackVersion = "1.2.3"
    // note: when the kotlinCompilerJvmTargetVersion value changes, make sure to also change the value in build.gradle.kts in buildSrc module
    // todo find out if it is (still) necessary to set the Kotlin compiler jvm target version (see community_salesforce_apis, where the jvm target version is NOT set)
    const val kotlinCompilerJvmTargetVersion = "11"
    // https://mvnrepository.com/artifact/org.junit.jupiter
    const val jUnitJupiterVersion = "5.7.2"
    // https://www.jacoco.org/jacoco/trunk/doc/changes.html
    const val jacocoToolVersion = "0.8.7"
}

object Dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    // https://github.com/kotlin/kotlinx.coroutines/blob/master/README.md#using-in-your-projects
    const val org_jetbrains_kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesVersion}"
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
    const val org_jetbrains_kotlinx_serialization_json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationVersion}"
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-datetime
    const val org_jetbrains_kotlinx_datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetimeVersion}"
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    const val ch_qos_logback_classic = "ch.qos.logback:logback-classic:${Versions.logbackVersion}"
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
    const val org_junit_jupiter = "org.junit.jupiter:junit-jupiter:${Versions.jUnitJupiterVersion}"
    // https://mvnrepository.com/artifact/org.junit/junit-bom
    const val org_junit_bom = "org.junit:junit-bom:${Versions.jUnitJupiterVersion}"
}
