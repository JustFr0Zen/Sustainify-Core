package de.unigoettingen.rating

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.WebApplicationException

@ApplicationScoped
class RatingService {
    @Inject
    lateinit var ratingRepository: RatingRepository

    fun getRating(company: String): RatingView? {
        val ratings = ratingRepository.getRatings(company)

        if (ratings.isEmpty()) {
            throw WebApplicationException("Rating could not be found", 404)
        }

        val averageRating = ratings.sumOf { it.rating ?: 0 } / ratings.size

        return RatingView(company.uppercase(), averageRating)
    }
}
