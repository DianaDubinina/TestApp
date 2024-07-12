package searchtickets.app.domain.usecases

import searchtickets.app.domain.models.Flights
import searchtickets.app.domain.repositories.FlightsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFlightsUseCase @Inject constructor(
    private val flightsRepository: FlightsRepository
) {
    suspend operator fun invoke(url: String): List<Flights> {
        return flightsRepository.getFlights(url)
    }
}