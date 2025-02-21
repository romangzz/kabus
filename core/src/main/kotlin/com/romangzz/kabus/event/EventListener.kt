package com.romangzz.kabus.event

/**
 * Component to listen to events published.
 */
fun interface EventListener<E : Event> {

    /**
     * Receives an event to make an action
     *
     * @param event received event
     */
    fun receive(event: E)
}