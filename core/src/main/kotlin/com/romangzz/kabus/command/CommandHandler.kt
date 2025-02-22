package com.romangzz.kabus.command

import com.romangzz.kabus.event.Event

/**
 * Handles the received command.
 *
 * @param C the command type.
 * @param E the event type.
 */
fun interface CommandHandler<C : Command<E>, E : Event> {
  suspend fun handle(command: C): E
}
