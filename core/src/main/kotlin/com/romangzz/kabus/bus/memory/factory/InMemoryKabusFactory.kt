package com.romangzz.kabus.bus.memory.factory

import com.romangzz.kabus.bus.KabusFactory
import com.romangzz.kabus.bus.memory.command.InMemoryCommandBus
import com.romangzz.kabus.bus.memory.command.InMemoryCommandRegistry
import com.romangzz.kabus.bus.memory.event.InMemoryEventBus
import com.romangzz.kabus.bus.memory.query.InMemoryQueryBus
import com.romangzz.kabus.bus.memory.query.InMemoryQueryRegistry
import com.romangzz.kabus.command.CommandBus
import com.romangzz.kabus.command.CommandHandler
import com.romangzz.kabus.configuration.KabusConfiguration
import com.romangzz.kabus.event.EventBus
import com.romangzz.kabus.query.QueryBus
import com.romangzz.kabus.query.QueryHandler

/**
 * Abstract factory class for creating in-memory implementations of Kabus components.
 *
 * @param configuration The configuration for the Kabus components.
 */
abstract class InMemoryKabusFactory(configuration: KabusConfiguration) :
    KabusFactory<KabusConfiguration>(configuration) {

  private val commandRegistry: InMemoryCommandRegistry = InMemoryCommandRegistry()
  private val commandBus: InMemoryCommandBus = InMemoryCommandBus(commandRegistry)
  private val queryRegistry: InMemoryQueryRegistry = InMemoryQueryRegistry()
  private val queryBus: InMemoryQueryBus = InMemoryQueryBus(queryRegistry)
  private val eventBus: InMemoryEventBus = InMemoryEventBus()

  /** Initializes the factory by setting up the command registry with command handlers. */
  fun initialize() {
    val handlerInstances = getCommandHandlerInstances()
    commandRegistry.initialize(configuration.basePackage) { _, handler ->
      handlerInstances.first { it::class.java == handler }
    }
    val queryHandlerInstances = getQueryHandlerInstances()
    queryRegistry.initialize(configuration.basePackage) { _, handler ->
      queryHandlerInstances.first { it::class.java == handler }
    }
  }

  /**
   * Returns the event bus.
   *
   * @return The in-memory event bus.
   */
  override fun getEventBus(): EventBus {
    return eventBus
  }

  /**
   * Returns the command bus.
   *
   * @return The in-memory command bus.
   */
  override fun getCommandBus(): CommandBus {
    return commandBus
  }

  /**
   * Returns the query bus.
   *
   * @return The in-memory query bus.
   */
  override fun getQueryBus(): QueryBus {
    return queryBus
  }

  /**
   * Abstract method to get instances of command handlers.
   *
   * @return A list of command handler instances.
   */
  abstract fun getCommandHandlerInstances(): List<CommandHandler<*, *>>

  /**
   * Abstract method to get instances of query handlers.
   *
   * @return A list of query handler instances.
   */
  abstract fun getQueryHandlerInstances(): List<QueryHandler<*, *>>
}
