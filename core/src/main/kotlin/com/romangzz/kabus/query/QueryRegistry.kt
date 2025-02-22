package com.romangzz.kabus.query

/** Interface representing a registry for query handlers. */
interface QueryRegistry {

  /**
   * Registers a query handler for a specific query.
   *
   * @param M The type of the model produced by the query.
   * @param Q The type of the query.
   * @param query The class of the query.
   * @param handler The query handler to register.
   */
  fun <M : QueryModel, Q : Query<M>> register(query: Class<Q>, handler: QueryHandler<Q, M>)

  /**
   * Retrieves the query handler for a specific query.
   *
   * @param M The type of the model produced by the query.
   * @param Q The type of the query.
   * @param query The query instance.
   * @return The query handler for the specified query.
   * @throws IllegalArgumentException if no handler is found for the query.
   */
  fun <M : QueryModel, Q : Query<M>> get(query: Q): QueryHandler<Q, M>
}
