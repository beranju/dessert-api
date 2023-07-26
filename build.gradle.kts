val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kgraphql_version: String by project
val koin_version: String by project
val kmongo_version: String by project
val bcrypt_version: String by project

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.2"
}

tasks{
    compileKotlin{
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin{
        kotlinOptions.jvmTarget = "11"
    }
}

group = "com.beran"
version = "0.0.1"
application {
    mainClass.set("com.beran.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")


    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("com.apurebase:kgraphql:$kgraphql_version")
    implementation("com.apurebase:kgraphql-ktor:$kgraphql_version")
//    implementation("io.ktor:ktor-auth:$ktor_version")
//    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("at.favre.lib:bcrypt:$bcrypt_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

