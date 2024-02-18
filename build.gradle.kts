plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.17.1"
}

group = "ru.sbrf.fraud.at"
version = "1.0.0"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2021.3.2")
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("213")
    untilBuild.set("213.*")
  }
}
