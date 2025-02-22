package com.romangzz.kabus.bus.memory.factory

import com.romangzz.kabus.command.Command
import com.romangzz.kabus.command.CommandHandler
import com.romangzz.kabus.configuration.memory.InMemoryKabusConfiguration
import com.romangzz.kabus.event.Event
import com.romangzz.kabus.query.Query
import com.romangzz.kabus.query.QueryHandler
import com.romangzz.kabus.query.QueryModel
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

  private class DummyQueryModel : QueryModel()

  private class DummyQuery : Query<DummyQueryModel>()

  private class DummyQueryHandler : QueryHandler<DummyQuery, DummyQueryModel> {
    override suspend fun handle(query: DummyQuery): DummyQueryModel {
      return DummyQueryModel()
    }
  }

  @Test
  fun `should create a new instance of InMemoryKabusFactory`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(
            configuration, listOf(DummyCommandHandler()), listOf(DummyQueryHandler()))

    assertNotNull(inMemoryKabusFactory)
  }

  @Test
  fun `should return an instance of InMemoryEventBus`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(
            configuration, listOf(DummyCommandHandler()), listOf(DummyQueryHandler()))

    val eventBus = inMemoryKabusFactory.getEventBus()

    assertNotNull(eventBus)
  }

  @Test
  fun `should return an instance of InMemoryCommandBus`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(
            configuration, listOf(DummyCommandHandler()), listOf(DummyQueryHandler()))

    val commandBus = inMemoryKabusFactory.getCommandBus()

    assertNotNull(commandBus)
  }

  @Test
  fun `should return an instance of InMemoryQueryBus`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(
            configuration, listOf(DummyCommandHandler()), listOf(DummyQueryHandler()))

    val queryBus = inMemoryKabusFactory.getQueryBus()

    assertNotNull(queryBus)
  }

  @Test
  fun `should initialize the factory`() {
    val configuration = InMemoryKabusConfiguration(this.javaClass.packageName)
    val inMemoryKabusFactory =
        DefaultInMemoryKabusFactory(
            configuration, listOf(DummyCommandHandler()), listOf(DummyQueryHandler()))

    assertDoesNotThrow { inMemoryKabusFactory.initialize() }
  }
}
