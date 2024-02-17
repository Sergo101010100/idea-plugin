plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.17.1"
}

group = "ru.sbrf.fraud.at"
version = "2.0.0"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2023.1.5")
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("231")
    untilBuild.set("233.*")
  }
}
