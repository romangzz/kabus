package com.romangzz.kabus.bus.memory.command

import com.romangzz.kabus.command.Command
import com.romangzz.kabus.command.CommandBus
import com.romangzz.kabus.event.Event
import kotlinx.coroutines.Deferred

class InMemoryCommandBus : CommandBus {

    override fun <E : Event, C : Command<E>> asyncExecute(command: C): Deferred<E> {
        TODO("Not yet implemented")
    }

    override fun <E : Event, C : Command<E>> syncExecute(command: C): E {
        TODO("Not yet implemented")
    }

}