package com.romangzz.kabus.bus.memory.command

import com.romangzz.kabus.command.Command
import com.romangzz.kabus.command.CommandHandler
import com.romangzz.kabus.event.Event
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class InMemoryCommandRegistryTest {

  private class DummyEvent : Event()

  private class DummyCommand : Command<DummyEvent>()

  private class DummyCommandHandler : CommandHandler<DummyCommand, DummyEvent> {
    override suspend fun handle(command: DummyCommand): DummyEvent {
      return DummyEvent()
    }
  }

  private val dummyCommand = DummyCommand()
  private val dummyCommandHandler = DummyCommandHandler()

  @Test
  fun `should create a new instance of InMemoryCommandRegistry`() {
    val inMemoryCommandRegistry = InMemoryCommandRegistry()

    assertNotNull(inMemoryCommandRegistry)
  }

  @Test
  fun `should initialize`() {
    val inMemoryCommandRegistry = InMemoryCommandRegistry()

    assertDoesNotThrow {
      inMemoryCommandRegistry.initialize(this.javaClass.packageName) { _, _ -> dummyCommandHandler }
    }
    assertNotNull(inMemoryCommandRegistry)
  }

  @Test
  fun `should register`() {
    val inMemoryCommandRegistry = InMemoryCommandRegistry()

    assertDoesNotThrow {
      inMemoryCommandRegistry.register(DummyCommand::class.java, dummyCommandHandler)
    }
    assertNotNull(inMemoryCommandRegistry)
  }

  @Test
  fun `should get`() {
    val inMemoryCommandRegistry = InMemoryCommandRegistry()

    inMemoryCommandRegistry.register(DummyCommand::class.java, dummyCommandHandler)

    assertDoesNotThrow { inMemoryCommandRegistry.get(dummyCommand) }
    assertNotNull(inMemoryCommandRegistry)
  }

  @Test
  fun `should throw error when handler not found`() {
    val inMemoryCommandRegistry = InMemoryCommandRegistry()

    assertNotNull(inMemoryCommandRegistry)
    assertThrows<IllegalArgumentException> { inMemoryCommandRegistry.get(dummyCommand) }
  }

  @Test
  fun `should throw error when handler already registered`() {
    val inMemoryCommandRegistry = InMemoryCommandRegistry()

    inMemoryCommandRegistry.register(DummyCommand::class.java, dummyCommandHandler)

    assertNotNull(inMemoryCommandRegistry)
    assertThrows<IllegalArgumentException> {
      inMemoryCommandRegistry.register(DummyCommand::class.java, dummyCommandHandler)
    }
  }
}
