package com.romangzz.kabus.bus.memory.query

import com.romangzz.kabus.query.Query
import com.romangzz.kabus.query.QueryHandler
import com.romangzz.kabus.query.QueryModel
import com.romangzz.kabus.query.QueryRegistry
import io.github.classgraph.ClassGraph
import java.lang.reflect.ParameterizedType

/** In-memory implementation of a query registry. */
class InMemoryQueryRegistry : QueryRegistry {

  private val handlers = mutableMapOf<Class<out Query<*>>, QueryHandler<*, *>>()

  /**
   * Initializes the query registry by scanning the specified packages for query handlers.
   *
   * @param packageNames The packages to scan for query handlers.
   * @param findInstance A function to find an instance of a query handler.
   */
  @Suppress("UNCHECKED_CAST")
  fun initialize(
      vararg packageNames: String,
      findInstance:
          (query: Class<Query<*>>, handler: Class<QueryHandler<*, *>>) -> QueryHandler<*, *>?
  ) {
    ClassGraph().enableAllInfo().acceptPackages(*packageNames).scan().use { scanResult ->
      val handlerClasses = scanResult.getClassesImplementing(QueryHandler::class.java.name)

      handlerClasses.forEach { handlerClassInfo ->
        val handlerClass = handlerClassInfo.loadClass() as Class<QueryHandler<*, *>>
        val genericInterface = handlerClass.genericInterfaces.firstOrNull() as? ParameterizedType
        if (genericInterface != null) {
          val queryClass = genericInterface.actualTypeArguments[1] as Class<Query<*>>
          val handler = findInstance(queryClass, handlerClass)
          if (handler != null) {
            handlers[queryClass] = handler
          }
        } else {
          error("Failed to extract query type for handler: ${handlerClass.name}")
        }
      }
    }
  }

  /**
   * Registers a query handler for a specific query.
   *
   * @param M The type of the model produced by the query.
   * @param Q The type of the query.
   * @param query The class of the query.
   * @param handler The query handler to register.
   */
  override fun <M : QueryModel, Q : Query<M>> register(
      query: Class<Q>,
      handler: QueryHandler<Q, M>
  ) {
    require(!handlers.containsKey(query)) { "Handler already registered" }
    handlers[query] = handler
  }

  /**
   * Retrieves the query handler for a specific query.
   *
   * @param M The type of the model produced by the query.
   * @param Q The type of the query.
   * @param query The query instance.
   * @return The query handler for the specified query.
   * @throws IllegalArgumentException if no handler is found for the query.
   */
  @Suppress("UNCHECKED_CAST")
  override fun <M : QueryModel, Q : Query<M>> get(query: Q): QueryHandler<Q, M> {
    val handler = handlers[query::class.java]
    require(handler != null) { "Handler not found" }
    return handler as QueryHandler<Q, M>
  }
}
