package com.romangzz.kabus.configuration.memory

import com.romangzz.kabus.configuration.KabusConfiguration
import com.romangzz.kabus.configuration.Type

/**
 * Configuration class for in-memory Kabus.
 *
 * @property basePackage The base package for the Kabus configuration.
 */
class InMemoryKabusConfiguration(override val basePackage: String) : KabusConfiguration {

  override val type: Type = Type.IN_MEMORY
}
