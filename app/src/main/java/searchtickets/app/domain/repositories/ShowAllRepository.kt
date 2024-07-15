package searchtickets.app.domain.repositories

import searchtickets.app.domain.models.All

interface ShowAllRepository {
    suspend fun getAll(url: String): List<All>
}