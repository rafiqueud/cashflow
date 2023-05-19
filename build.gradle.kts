plugins {
    java
    id("org.springframework.boot") version "3.0.7"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("kapt") version "1.8.21"
    kotlin("jvm") version "1.8.21"
    jacoco
}

group = "com.cash"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.2"
extra["testcontainersVersion"] = "1.18.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Metrics
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus:1.11.0")

    // Database
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // Database migration
    implementation("org.liquibase:liquibase-core")

    // YAML log4 config
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")

    // OpenApi Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")


    // MapStruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.mapstruct:mapstruct-processor:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.testcontainers:junit-jupiter")

    testImplementation("org.testcontainers:vault")
    testImplementation("org.testcontainers:postgresql")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
    doLast {
        println("View test results at:")
        println("file://$buildDir/reports/tests/test/index.html")
        println("View code coverage at:")
        println("file://$buildDir/reports/jacoco/test/html/index.html")
    }
}


