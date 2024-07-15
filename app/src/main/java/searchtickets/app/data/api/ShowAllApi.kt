package searchtickets.app.data.api

import retrofit2.http.GET
import retrofit2.http.Url

interface ShowAllApi {
    @GET
    suspend fun getAll(@Url url: String): ResponseAll
}