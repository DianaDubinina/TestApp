package searchtickets.app.domain.repositories

import searchtickets.app.domain.models.Info

interface ImagesRepository {
    suspend fun getInfo(url: String): List<Info>
}