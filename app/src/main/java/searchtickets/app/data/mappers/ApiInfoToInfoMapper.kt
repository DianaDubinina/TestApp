package searchtickets.app.data.mappers

import searchtickets.app.data.api.ApiListOfInfo
import searchtickets.app.domain.models.Info
import javax.inject.Inject

class ApiInfoToInfoMapper @Inject constructor() : Mapper<ApiListOfInfo, Info> {
    override fun mapToDomain(entity: ApiListOfInfo): Info {
        return Info(
            id = entity.id,
            title = entity.title,
            town = entity.town,
            price = entity.price.value
        )
    }
}