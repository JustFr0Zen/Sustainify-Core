package de.unigoettingen.company

import de.unigoettingen.api.database.Jooq
import de.unigoettingen.jooq.tables.Companies.COMPANIES
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CompanyRepository {
    @Inject
    lateinit var jooq: Jooq

    fun insertCompany(company: CompanyView) {
        jooq.dsl { db ->
            db.insertInto(COMPANIES)
                .set(jooq.propertiesOf(company))
                .execute()
        }
    }
}
