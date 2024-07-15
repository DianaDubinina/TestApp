package searchtickets.app.domain.usecases

import searchtickets.app.domain.models.All
import searchtickets.app.domain.repositories.ShowAllRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllUseCase @Inject constructor(
    private val flightsRepository: ShowAllRepository
) {
    suspend operator fun invoke(url: String): List<All> {
        return flightsRepository.getAll(url)
    }
}