package de.unigoettingen.company

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CompanyService {
    @Inject
    lateinit var companyRepository: CompanyRepository

    fun insertCompany(companyView: CompanyView) = companyRepository.insertCompany(companyView)
}
