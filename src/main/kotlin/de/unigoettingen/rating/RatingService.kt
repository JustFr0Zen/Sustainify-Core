package de.unigoettingen.rating

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.WebApplicationException

@ApplicationScoped
class RatingService {
    @Inject
    lateinit var ratingRepository: RatingRepository

    fun getRating(company: String): RatingView? {
        val rating = ratingRepository.getLastRating(company) ?: throw WebApplicationException("Rating could not be found", 404)

        return RatingView(company.uppercase(), rating.rating)
    }
}
