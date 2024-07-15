package searchtickets.app.data.mappers

import searchtickets.app.data.api.ApiListOfAll
import searchtickets.app.domain.models.All
import javax.inject.Inject

class ApiAllToAllMapper @Inject constructor() : Mapper<ApiListOfAll, All> {
    override fun mapToDomain(entity: ApiListOfAll): All {
        return All(
            id = entity.id,
            badge = entity.badge,
            company = entity.company,
            departure = entity.departure,
            arrival = entity.arrival,
            price = entity.price,
            has_transfer = entity.has_transfer
        )
    }
}