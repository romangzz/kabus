package com.romangzz.kabus.command

import com.romangzz.kabus.event.Event

/** Interface representing a registry for command handlers. */
interface CommandRegistry {

  /**
   * Registers a command handler for a specific command.
   *
   * @param E The type of the event produced by the command.
   * @param C The type of the command.
   * @param command The class of the command.
   * @param handler The command handler to register.
   */
  fun <E : Event, C : Command<E>> register(command: Class<C>, handler: CommandHandler<C, E>)

  /**
   * Retrieves the command handler for a specific command.
   *
   * @param E The type of the event produced by the command.
   * @param C The type of the command.
   * @param command The command instance.
   * @return The command handler for the specified command.
   * @throws IllegalArgumentException if no handler is found for the command.
   */
  fun <E : Event, C : Command<E>> get(command: C): CommandHandler<C, E>
}
