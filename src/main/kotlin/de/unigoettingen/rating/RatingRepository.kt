package de.unigoettingen.rating

import de.unigoettingen.api.database.Jooq
import de.unigoettingen.jooq.tables.Ratings.RATINGS
import de.unigoettingen.jooq.tables.pojos.Ratings
import org.jooq.impl.DSL.max
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class RatingRepository {
    @Inject
    lateinit var jooq: Jooq

    fun getLastRating(company: String): Ratings? {
        return jooq.dsl { db ->
            db.selectFrom(RATINGS)
                .where(
                    RATINGS.COMPANY.eq(company.uppercase())
                        .and(
                            RATINGS.INSERTED_AT.eq(
                                db.select(max(RATINGS.INSERTED_AT).`as`("max"))
                                    .from(RATINGS)
                                    .where(RATINGS.COMPANY.eq(company.uppercase()))
                                    .fetchOne("max")?.let { it as Timestamp } ?: Timestamp.valueOf(LocalDateTime.now())
                            )
                        )
                )
                .fetchOneInto(Ratings::class.java)
        }
    }
}
