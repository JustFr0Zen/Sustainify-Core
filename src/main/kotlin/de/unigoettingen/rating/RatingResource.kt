package de.unigoettingen.rating

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/rating")
@Produces(APPLICATION_JSON)
class RatingResource {
    @Inject
    lateinit var ratingService: RatingService

    @GET
    @Path("/{company}")
    fun getRating(company: String) = ratingService.getRating(company)
}
