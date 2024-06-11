plugins {
    java
    id("org.springframework.boot") version "2.2.13.RELEASE"
    id("io.spring.dependency-management") version "1.1.3"
    `maven-publish`
}

group = "com.github.tiennm99"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

configurations {
    val compileOnly by getting {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")

    compileOnly("org.projectlombok:lombok")

    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("org.springframework.boot:spring-boot-starter:2.2.13.RELEASE")
    implementation("org.springframework.data:spring-data-couchbase:3.2.13.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tiennm99/mitisrv")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("MiTiSrv")
                description.set("A game server created by miti99")
                url.set("https://tiennm99.github.io/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("tiennm99")
                        name.set("Tien Nguyen Minh")
                        email.set("tiennm99@outlook.com")
                    }
                }
            }
        }
    }
}
