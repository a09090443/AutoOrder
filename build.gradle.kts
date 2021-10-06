import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("kapt") version "1.5.31"
    application
}

group = "com.zipe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.quartz-scheduler:quartz:2.3.2")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.3.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("info.picocli:picocli:4.6.1")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("org.jsoup:jsoup:1.14.2")

    kapt("info.picocli:picocli-codegen:4.6.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
