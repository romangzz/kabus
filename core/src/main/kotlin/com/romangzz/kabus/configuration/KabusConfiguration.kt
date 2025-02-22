package com.romangzz.kabus.configuration

/** Interface representing the configuration for Kabus. */
interface KabusConfiguration {

  /** The type of the Kabus configuration. */
  val type: Type

  /** The base package for the Kabus configuration. */
  val basePackage: String
}
