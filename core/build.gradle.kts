dependencies {
  implementation(kotlin("stdlib"))
  implementation(
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.extra["coroutinesVersion"]}")
  implementation("io.github.classgraph:classgraph:${rootProject.extra["classgraphVersion"]}")
}
