package com.romangzz.kabus.bus

import com.romangzz.kabus.command.CommandBus
import com.romangzz.kabus.configuration.KabusConfiguration
import com.romangzz.kabus.event.EventBus
import com.romangzz.kabus.query.QueryBus

/**
 * Abstract factory class for creating Kabus instances.
 *
 * @param C The type of Kabus configuration.
 * @property configuration The configuration for the Kabus instance.
 */
abstract class KabusFactory<C : KabusConfiguration>(protected open val configuration: C) {

  /**
   * Returns the event bus instance.
   *
   * @return The event bus instance.
   */
  abstract fun getEventBus(): EventBus

  /**
   * Returns the command bus instance.
   *
   * @return The command bus instance.
   */
  abstract fun getCommandBus(): CommandBus

  /**
   * Returns the query bus instance.
   *
   * @return The query bus instance.
   */
  abstract fun getQueryBus(): QueryBus
}
