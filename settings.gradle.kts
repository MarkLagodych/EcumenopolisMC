pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

// Should match the mod ID
rootProject.name = "ecumenopolismc"

include("lib", "tools")
