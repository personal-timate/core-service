import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.timate"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	/* Security */
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-oauth2-resource-server:5.7.3")
	implementation("org.springframework.security.oauth:spring-security-oauth2:2.5.2.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client:2.7.4")

	/* Database(s) */
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	implementation("org.liquibase:liquibase-core:4.16.1")
	implementation("org.springframework.data:spring-data-jdbc:2.4.3")

	/* API */
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.11")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.11")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
	implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.4")

	/* Object Mapping */
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.openapitools:jackson-databind-nullable:0.2.3")

	/* General */
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	/* Logging */
	implementation("org.apache.logging.log4j:log4j-jcl")
	implementation("org.zalando:logbook-spring-boot-starter:2.14.0")

	/* Testing */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

tasks.withType<Test> {
	useJUnitPlatform()
}

springBoot {
	buildInfo()
}