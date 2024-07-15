package searchtickets.app.data.repositories

import searchtickets.app.data.api.ShowAllApi
import searchtickets.app.data.mappers.ApiAllToAllMapper
import searchtickets.app.domain.models.All
import searchtickets.app.domain.repositories.ShowAllRepository

class ShowAllRepositoryImpl(
    private val api: ShowAllApi,
    private val mapper: ApiAllToAllMapper
) : ShowAllRepository {
    override suspend fun getAll(url: String): List<All> {
        return api.getAll(url).offers.map {
            mapper.mapToDomain(it)
        }
    }
}