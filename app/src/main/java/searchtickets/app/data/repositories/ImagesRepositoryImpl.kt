package searchtickets.app.data.repositories

import searchtickets.app.data.api.ImagesApi
import searchtickets.app.data.mappers.ApiInfoToInfoMapper
import searchtickets.app.domain.models.Info
import searchtickets.app.domain.repositories.ImagesRepository
class ImagesRepositoryImpl(
    private val api: ImagesApi,
    private val mapper: ApiInfoToInfoMapper
) : ImagesRepository {
    override suspend fun getInfo(url: String): List<Info> {
        return api.getInfo(url).offers.map {
            mapper.mapToDomain(it)
        }
    }
}