package searchtickets.app.data.mappers

import searchtickets.app.data.api.ApiListOfFlights
import searchtickets.app.domain.models.Flights
import javax.inject.Inject

class ApiFlightsToFlightsMapper @Inject constructor() : Mapper<ApiListOfFlights, Flights> {
    override fun mapToDomain(entity: ApiListOfFlights): Flights {
        return Flights(
            id = entity.id,
            title = entity.title,
            time_range = entity.timeRange.value,
            price = entity.price.value
        )
    }
}