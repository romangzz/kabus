package com.romangzz.kabus.event

import java.time.Instant

/**
 * Represents an event published by a command.
 *
 * @property timestamp The time the event occurred.
 */
open class Event(private val timestamp: Instant = Instant.now())
