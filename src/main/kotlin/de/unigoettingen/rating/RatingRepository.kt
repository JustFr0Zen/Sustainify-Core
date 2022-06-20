package de.unigoettingen.rating

import de.unigoettingen.api.database.Jooq
import de.unigoettingen.jooq.tables.Ratings.RATINGS
import de.unigoettingen.jooq.tables.pojos.Ratings
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class RatingRepository {
    @Inject
    lateinit var jooq: Jooq

    fun getRatings(company: String): List<Ratings> {
        return jooq.dsl { db ->
            db.selectFrom(RATINGS)
                .where(RATINGS.COMPANY.eq(company.uppercase()))
                .fetchInto(Ratings::class.java)
        }
    }
}
