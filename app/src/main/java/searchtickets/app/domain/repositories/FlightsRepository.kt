package searchtickets.app.domain.repositories

import searchtickets.app.domain.models.Flights

interface FlightsRepository {
    suspend fun getFlights(url: String): List<Flights>
}