package searchtickets.app.domain.usecases

import searchtickets.app.domain.models.Info
import searchtickets.app.domain.repositories.ImagesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetImagesUseCase @Inject constructor(
    private val imagesRepository: ImagesRepository
) {
    suspend operator fun invoke(url: String): List<Info> {
        return imagesRepository.getInfo(url)
    }
}