package searchtickets.app.data.repositories

import searchtickets.app.data.api.FlightsApi
import searchtickets.app.data.mappers.ApiFlightsToFlightsMapper
import searchtickets.app.domain.models.Flights
import searchtickets.app.domain.repositories.FlightsRepository

class FlightsRepositoryImpl(
    private val api: FlightsApi,
    private val mapper: ApiFlightsToFlightsMapper
) : FlightsRepository {
    override suspend fun getFlights(url: String): List<Flights> {
        return api.getFlighs(url).offers.map {
            mapper.mapToDomain(it)
        }
    }
}