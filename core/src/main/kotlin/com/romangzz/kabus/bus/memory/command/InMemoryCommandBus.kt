package com.romangzz.kabus.bus.memory.command

import com.romangzz.kabus.command.Command
import com.romangzz.kabus.command.CommandBus
import com.romangzz.kabus.event.Event
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

/**
 * In-memory implementation of the CommandBus interface.
 *
 * @property commandRegistry The registry for command handlers.
 */
class InMemoryCommandBus(private val commandRegistry: InMemoryCommandRegistry) : CommandBus {

  /**
   * Executes a command asynchronously.
   *
   * @param E The type of the event produced by the command.
   * @param C The type of the command.
   * @param command The command to be executed.
   * @return A deferred result of the event produced by the command.
   */
  override suspend fun <E : Event, C : Command<E>> asyncExecute(command: C) = coroutineScope {
    async {
      val handler = commandRegistry.get(command)
      handler.handle(command)
    }
  }

  /**
   * Executes a command synchronously.
   *
   * @param E The type of the event produced by the command.
   * @param C The type of the command.
   * @param command The command to be executed.
   * @return The event produced by the command.
   */
  override fun <E : Event, C : Command<E>> syncExecute(command: C): E {
    val handler = commandRegistry.get(command)
    return runBlocking { handler.handle(command) }
  }
}
