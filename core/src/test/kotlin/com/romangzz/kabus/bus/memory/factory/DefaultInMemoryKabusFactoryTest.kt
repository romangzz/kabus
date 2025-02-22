package com.romangzz.kabus.bus.memory.factory

import com.romangzz.kabus.command.Command
import com.romangzz.kabus.command.CommandHandler
import com.romangzz.kabus.configuration.memory.InMemoryKabusConfiguration
import com.romangzz.kabus.event.Event
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DefaultInMemoryKabusFactoryTest {

  private class DummyEvent : Event()

  private class DummyCommand : Command<DummyEvent>()

  private class DummyCommandHandler : CommandHandler<DummyCommand, DummyEvent> {
    override suspend fun handle(command: DummyCommand): DummyEvent {
      return DummyEvent()
    }
  }

  @Test
  fun `should create a new instance of InMemoryKabusFactory`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(configuration, listOf(DummyCommandHandler()))

    assertNotNull(inMemoryKabusFactory)
  }

  @Test
  fun `should return an instance of InMemoryEventBus`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(configuration, listOf(DummyCommandHandler()))

    val eventBus = inMemoryKabusFactory.getEventBus()

    assertNotNull(eventBus)
  }

  @Test
  fun `should return an instance of InMemoryCommandBus`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(configuration, listOf(DummyCommandHandler()))

    val commandBus = inMemoryKabusFactory.getCommandBus()

    assertNotNull(commandBus)
  }

  @Test
  fun `should return an instance of InMemoryQueryBus`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(configuration, listOf(DummyCommandHandler()))

    val queryBus = inMemoryKabusFactory.getQueryBus()

    assertNotNull(queryBus)
  }

  @Test
  fun `should initialize the factory`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(configuration, listOf(DummyCommandHandler()))

    assertDoesNotThrow { inMemoryKabusFactory.initialize() }
  }
}
