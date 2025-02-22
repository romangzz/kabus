package com.romangzz.kabus.bus.memory.event

import com.romangzz.kabus.event.Event
import com.romangzz.kabus.event.EventBus

/** In-memory implementation of the EventBus interface. */
class InMemoryEventBus : EventBus {

  /**
   * Publishes an event to the in-memory event bus.
   *
   * @param event The event to be published.
   */
  override suspend fun publish(event: Event) {
    TODO("Not yet implemented")
  }
}
