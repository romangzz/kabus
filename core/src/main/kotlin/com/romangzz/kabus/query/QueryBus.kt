package com.romangzz.kabus.query

import kotlinx.coroutines.Deferred

/** Manages query execution. */
interface QueryBus {

  /**
   * Executes the query asynchronously.
   *
   * @param query The query to execute.
   * @return A future representing the query result.
   */
  fun <R : QueryModel, C : Query<R>> asyncExecute(query: C): Deferred<R>

  /**
   * Executes the query synchronously.
   *
   * @param query The query to execute.
   * @return The query result.
   */
  fun <R : QueryModel, C : Query<R>> syncExecute(query: C): R
}
