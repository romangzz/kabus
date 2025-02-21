package com.romangzz.kabus.bus.memory.query

import com.romangzz.kabus.query.Query
import com.romangzz.kabus.query.QueryBus
import com.romangzz.kabus.query.QueryModel
import kotlinx.coroutines.Deferred

class InMemoryQueryBus : QueryBus {

    override fun <R : QueryModel, C : Query<R>> asyncExecute(query: C): Deferred<R> {
        TODO("Not yet implemented")
    }

    override fun <R : QueryModel, C : Query<R>> syncExecute(query: C): R {
        TODO("Not yet implemented")
    }

}