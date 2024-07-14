package searchtickets.app.data.api

import retrofit2.http.GET
import retrofit2.http.Url

interface FlightsApi {
    @GET
    suspend fun getFlighs(@Url url: String): ResponseFlights
}