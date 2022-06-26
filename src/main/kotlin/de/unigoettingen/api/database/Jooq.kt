package de.unigoettingen.api.database

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.name
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.sql.DataSource
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@ApplicationScoped
class Jooq {
    @Inject
    lateinit var dataSource: DataSource

    fun <R> dsl(function: (DSLContext) -> R): R {
        return DSL.using(dataSource, SQLDialect.POSTGRES).run(function)
    }

    fun <R : Any> propertiesOf(entity: R) = entity::class.memberProperties.associate { property ->
        property.isAccessible = true
        field(name(property.name.camelToSnakeCase())) to property.getter.call(entity)
    }.filterValues { it != null }

    // https://stackoverflow.com/questions/60010298/how-can-i-convert-a-camel-case-string-to-snake-case-and-back-in-idiomatic-kotlin
    private val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()

    fun String.camelToSnakeCase(): String {
        return camelRegex.replace(this) {
            "_${it.value}"
        }.lowercase()
    }
}
