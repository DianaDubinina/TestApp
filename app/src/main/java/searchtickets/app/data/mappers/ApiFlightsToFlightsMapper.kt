package searchtickets.app.data.mappers

import android.util.Log
import searchtickets.app.data.api.ApiListOfFlights
import searchtickets.app.domain.models.Flights
import javax.inject.Inject

class ApiFlightsToFlightsMapper @Inject constructor() : Mapper<ApiListOfFlights, Flights> {
    private val TAG = "ApiFlightsToFlightsMapper"

    override fun mapToDomain(entity: ApiListOfFlights): Flights {
        Log.d(TAG, "entity.timeRange: $entity.timeRange")
        return Flights(
            id = entity.id,
            title = entity.title,
            timeRange = entity.timeRange,
            price = entity.price.value
        )
    }
}