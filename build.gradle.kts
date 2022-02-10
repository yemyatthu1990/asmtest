plugins {
    kotlin("jvm") version "1.6.10"
    `maven-publish`
    `java-gradle-plugin`
}

group = "co.elastic.android.gradle"
version = "0.15"

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
    implementation("com.android.tools.build:gradle:7.1.0")
    compileOnly("org.ow2.asm:asm-util:9.0")
    compileOnly("org.ow2.asm:asm-commons:9.0")
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}