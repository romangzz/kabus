package com.romangzz.kabus.bus.memory.factory

import com.romangzz.kabus.command.CommandHandler
import com.romangzz.kabus.configuration.memory.InMemoryKabusConfiguration

/**
 * Factory class for creating in-memory Kabus instances.
 *
 * @property configuration The configuration for the in-memory Kabus.
 * @property commandHandlerInstances The list of command handler instances.
 */
class DefaultInMemoryKabusFactory(
    override val configuration: InMemoryKabusConfiguration,
    private val commandHandlerInstances: List<CommandHandler<*, *>>
) : InMemoryKabusFactory(configuration) {

  /**
   * Returns the list of command handler instances.
   *
   * @return The list of command handler instances.
   */
  override fun getCommandHandlerInstances(): List<CommandHandler<*, *>> {
    return commandHandlerInstances
  }
}
