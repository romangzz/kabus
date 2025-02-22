import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "2.1.10"
  id("maven-publish")
  id("org.jetbrains.dokka") version "2.0.0"
  id("com.diffplug.spotless") version "7.0.2"
}

val junitVersion by extra("5.10.0")
val coroutinesVersion by extra("1.10.1")
val classgraphVersion by extra("4.8.179")

allprojects {
  apply(plugin = "com.diffplug.spotless")

  repositories { mavenCentral() }

  configure<SpotlessExtension> {
    kotlin { ktfmt().metaStyle().configure {} }
    kotlinGradle { ktfmt().metaStyle().configure {} }
  }
}

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.dokka")

  dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.extra["junitVersion"]}")
  }

  tasks.named<Test>("test") { useJUnitPlatform() }

  tasks.withType<KotlinCompile>().configureEach {
    compilerOptions { jvmTarget.set(JvmTarget.JVM_21) }
  }
}
