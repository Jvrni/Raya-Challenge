enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Raya-Challenge"
include(":androidApp")
include(":shared")
include(":core:service")
include(":core")
include(":core:common:designSystem")
include(":core:common:navigation")
include(":core:common")
include(":core:domain")
