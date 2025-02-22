package com.romangzz.kabus.bus.memory.query

import com.romangzz.kabus.query.Query
import com.romangzz.kabus.query.QueryBus
import com.romangzz.kabus.query.QueryModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

/** In-memory implementation of a query bus. */
class InMemoryQueryBus(private val registry: InMemoryQueryRegistry) : QueryBus {

  /**
   * Asynchronously executes a query.
   *
   * @param R The type of the model produced by the query.
   * @param C The type of the query.
   * @param query The query to execute.
   * @return A deferred result of the query execution.
   */
  override suspend fun <R : QueryModel, C : Query<R>> asyncExecute(query: C) = coroutineScope {
    async {
      val handler = registry.get(query)
      handler.handle(query)
    }
  }

  /**
   * Synchronously executes a query.
   *
   * @param R The type of the model produced by the query.
   * @param C The type of the query.
   * @param query The query to execute.
   * @return The result of the query execution.
   */
  override fun <R : QueryModel, C : Query<R>> syncExecute(query: C): R {
    val handler = registry.get(query)
    return runBlocking { handler.handle(query) }
  }
}
