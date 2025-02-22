package com.romangzz.kabus.bus.memory.query

import com.romangzz.kabus.query.Query
import com.romangzz.kabus.query.QueryHandler
import com.romangzz.kabus.query.QueryModel
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class InMemoryQueryRegistryTest {
  private class DummyQueryModel : QueryModel()

  private class DummyQuery : Query<DummyQueryModel>()

  private class DummyQueryHandler : QueryHandler<DummyQuery, DummyQueryModel> {
    override suspend fun handle(query: DummyQuery): DummyQueryModel {
      return DummyQueryModel()
    }
  }

  private val dummyQuery = DummyQuery()
  private val dummyQueryHandler = DummyQueryHandler()

  @Test
  fun `should create a new instance of InMemoryQueryRegistry`() {
    val inMemoryQueryRegistry = InMemoryQueryRegistry()

    assertNotNull(inMemoryQueryRegistry)
  }

  @Test
  fun `should initialize`() {
    val inMemoryQueryRegistry = InMemoryQueryRegistry()

    assertDoesNotThrow {
      inMemoryQueryRegistry.initialize(this.javaClass.packageName) { _, _ -> dummyQueryHandler }
    }
    assertNotNull(inMemoryQueryRegistry)
  }

  @Test
  fun `should register`() {
    val inMemoryQueryRegistry = InMemoryQueryRegistry()

    assertDoesNotThrow { inMemoryQueryRegistry.register(DummyQuery::class.java, dummyQueryHandler) }
    assertNotNull(inMemoryQueryRegistry)
  }

  @Test
  fun `should get`() {
    val inMemoryQueryRegistry = InMemoryQueryRegistry()

    inMemoryQueryRegistry.register(DummyQuery::class.java, dummyQueryHandler)

    assertDoesNotThrow { inMemoryQueryRegistry.get(dummyQuery) }
    assertNotNull(inMemoryQueryRegistry)
  }

  @Test
  fun `should throw error when handler not found`() {
    val inMemoryQueryRegistry = InMemoryQueryRegistry()

    assertNotNull(inMemoryQueryRegistry)
    assertThrows<IllegalArgumentException> { inMemoryQueryRegistry.get(dummyQuery) }
  }

  @Test
  fun `should throw error when handler already registered`() {
    val inMemoryQueryRegistry = InMemoryQueryRegistry()

    inMemoryQueryRegistry.register(DummyQuery::class.java, dummyQueryHandler)

    assertNotNull(inMemoryQueryRegistry)
    assertThrows<IllegalArgumentException> {
      inMemoryQueryRegistry.register(DummyQuery::class.java, dummyQueryHandler)
    }
  }
}
