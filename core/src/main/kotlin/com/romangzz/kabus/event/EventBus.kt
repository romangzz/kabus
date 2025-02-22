package com.romangzz.kabus.event

/** Manages event publishing. */
fun interface EventBus {

  /**
   * Publishes an event.
   *
   * @param event The event to publish.
   */
  suspend fun publish(event: Event)
}
