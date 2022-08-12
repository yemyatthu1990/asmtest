import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    `maven-publish`
    `java-gradle-plugin`
}

group = "co.elastic.android.gradle"
version = "0.111"

repositories {
    mavenCentral()
    google()
}

gradlePlugin{
    plugins{
        create("simplePlugin") {
            id = "co.elastic.android.gradle"
            implementationClass = "co.elastic.android.gradle.ElasticPlugin"
        }
    }
}
dependencies {
    implementation("com.android.tools.build:gradle:7.2.0")
    compileOnly("org.ow2.asm:asm-util:9.0")
    compileOnly("org.ow2.asm:asm-commons:9.0")
    implementation(kotlin("stdlib"))
}
configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}