plugins {
    id("java-library")
    id("maven-publish")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "lib"
        }
    }
}
