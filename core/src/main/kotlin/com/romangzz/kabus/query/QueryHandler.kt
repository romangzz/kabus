package com.romangzz.kabus.query

/**
 * Handles the received query.
 *
 * @param Q the query type.
 * @param R the response type.
 */
fun interface QueryHandler<Q : Query<R>, R : QueryModel> {

    /**
     * Handles the query.
     *
     * @param query The query to handle.
     */
    suspend fun handle(query: Q): R
}