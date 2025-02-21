package com.romangzz.kabus.command

import com.romangzz.kabus.event.Event

/**
 * Represents a command that performs an action or triggers a process.
 *
 * @param E the type of event this command will publish.
 */
open class Command<E : Event>