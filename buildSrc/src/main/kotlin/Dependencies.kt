object Versions {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin
    const val kotlinVersion = "1.4.20"
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx
    const val kotlinxCoroutinesVersion = "1.4.2"
    const val kotlinxSerializationVersion = "1.0.1"
    const val kotlinxDatetimeVersion = "0.1.1"
    // https://mvnrepository.com/artifact/ch.qos.logback
    const val logbackVersion = "1.2.3"
    // note: when the kotlinCompilerJvmTargetVersion value changes, make sure to also change the value in build.gradle.kts in buildSrc module
    // todo find out if it is (still) necessary to set the Kotlin compiler jvm target version (see community_salesforce_apis, where the jvm target version is NOT set)
    const val kotlinCompilerJvmTargetVersion = "11"
}

object Dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    // https://github.com/kotlin/kotlinx.coroutines/blob/master/README.md#using-in-your-projects
    const val org_jetbrains_kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesVersion}"
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json
    const val org_jetbrains_kotlinx_serialization_json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationVersion}"
    const val org_jetbrains_kotlinx_datetime = "org.jetbrains.kotlinx:kotlinx-datetime-jvm:${Versions.kotlinxDatetimeVersion}"
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    const val ch_qos_logback_classic = "ch.qos.logback:logback-classic:${Versions.logbackVersion}"
}