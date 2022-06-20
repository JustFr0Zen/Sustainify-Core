package de.unigoettingen.api.database

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.sql.DataSource

@ApplicationScoped
class Jooq {
    @Inject
    lateinit var dataSource: DataSource

    fun <R> dsl(function: (DSLContext) -> R): R {
        return DSL.using(dataSource, SQLDialect.POSTGRES).run(function)
    }
}
