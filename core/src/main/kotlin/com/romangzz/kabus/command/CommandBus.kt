package com.romangzz.kabus.command

import com.romangzz.kabus.event.Event
import kotlinx.coroutines.Deferred

/** Manages command execution. */
interface CommandBus {

  /**
   * Executes the command asynchronously.
   *
   * @param command The command to execute.
   * @return A future representing the published event.
   */
  suspend fun <E : Event, C : Command<E>> asyncExecute(command: C): Deferred<E>

  /**
   * Executes the command synchronously.
   *
   * @param command The command to execute.
   * @return The published event.
   */
  fun <E : Event, C : Command<E>> syncExecute(command: C): E
}
