package searchtickets.app.data.api

import retrofit2.http.GET
import retrofit2.http.Url

interface ImagesApi {
    @GET
    suspend fun getInfo(@Url url: String): ResponseImages
}