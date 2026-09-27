plugins {
    java
    id("net.fabricmc.fabric-loom")
    `maven-publish`
}

version = providers.gradleProperty("mod_version").get()
group = providers.gradleProperty("maven_group").get()

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
}

sourceSets {
    create("algorithms") {
        java.srcDir("src/algorithms/java")
    }

    getByName("main") {
        compileClasspath += sourceSets["algorithms"].output
        runtimeClasspath += sourceSets["algorithms"].output
    }

    create("demos") {
        java.srcDir("src/demos/java")

        compileClasspath += sourceSets["algorithms"].output
        runtimeClasspath += sourceSets["algorithms"].output
    }
}

loom {
    splitEnvironmentSourceSets()

    mods {
        register("ecumenopolismc") {
            sourceSet(sourceSets.main.get())
            sourceSet(sourceSets.getByName("client"))
        }
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${providers.gradleProperty("minecraft_version").get()}")
    implementation("net.fabricmc:fabric-loader:${providers.gradleProperty("loader_version").get()}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    implementation(
        "net.fabricmc.fabric-api:fabric-api:${
            providers.gradleProperty("fabric_api_version").get()
        }"
    )

    implementation(sourceSets["algorithms"].output)

    add("demosImplementation", "org.processing:core:4.5.6")
}

listOf("WFC2DDemo").forEach { demoName ->
    tasks.register<JavaExec>("run$demoName") {
        description = "Run the $demoName demo."
        group = "demos"
        classpath = sourceSets["demos"].runtimeClasspath
        mainClass = demoName
    }
}

tasks.processResources {
    val version = version
    inputs.property("version", version)

    filesMatching("fabric.mod.json") {
        expand("version" to version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 25
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
    val projectName = project.name
    inputs.property("projectName", projectName)

    from(sourceSets["algorithms"].output)

    from("LICENSE.txt") {
        rename { "LICENSE-$projectName.txt" }
    }
}

// configure the maven publication
publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
