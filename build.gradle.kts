plugins {
    java
    idea
    `maven-publish`
}

group = "io.github.tiennm99"
version = "0.0.1-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    testCompileOnly {
        extendsFrom(configurations.testAnnotationProcessor.get())
    }
}

dependencies {
    val classgraphVersion = "4.8.177"
    val gsonVersion = "2.11.0"
    val junitVersion = "5.11.3"
    val junitPlatformVersion = "1.11.3"
    val log4jVersion = "2.24.1"
    val lombokVersion = "1.18.34"

    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")

    implementation("com.google.code.gson:gson:$gsonVersion")

    implementation("io.github.classgraph:classgraph:$classgraphVersion")

    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:$log4jVersion")

    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    testImplementation("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
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
